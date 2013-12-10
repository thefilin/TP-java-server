package frontend;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import utils.CookieDescriptor;
import utils.SHA2;
import utils.SysInfo;
import utils.TemplateHelper;
import utils.TimeHelper;

import base.Frontend;
import base.MessageSystem;
import dbService.UserDataSet;


public class FrontendImpl extends AbstractHandler implements Frontend{
	
	final private FrontendModel frontendModel;

	enum status { nothing, haveCookie, haveCookieAndPost, waiting, ready, notFound, rules, admin }

	public FrontendImpl(MessageSystem msgSystem){
		frontendModel = new FrontendModel(msgSystem);
	}

	private status getStatus(HttpServletRequest request,String target,status stat,String sessionId){
		if((stat.equals(status.haveCookie))&&(request.getMethod().equals("POST")))
			stat=status.haveCookieAndPost;
		if((stat.equals(status.haveCookie))&&(UserDataImpl.getUserSessionBySessionId(sessionId).getId()!=0))
			stat=status.ready;
		if (target.equals("/wait")){
			if((!stat.equals(status.haveCookie)&&(!stat.equals(status.haveCookieAndPost)))
					||(UserDataImpl.getUserSessionBySessionId(sessionId).getPostStatus()==0))
				stat=status.nothing;
			else 
				stat=status.waiting;
		}
		return stat;
	}

	private boolean inWeb(String target){
		return ((target.equals("/"))||(target.equals("/wait"))||(target.equals("/game"))||(target.equals("/profile"))
				||(target.equals("/admin"))||(target.equals("/rules"))||(target.equals("/logout"))||(target.equals("/reg")));
	}

	private boolean isStatic(String target){
		if(target.length()<4)
			return false;
		else if(target.length()==4)
			return target.substring(0, 4).equals("/js/");
		else return (((target.substring(0, 5)).equals("/img/"))||((target.substring(0, 5)).equals("/css/")));
	}

	private boolean newUser(String strSessionId, String strStartServerTime){
		return((strSessionId==null)||(strStartServerTime==null)
				||(!UserDataImpl.checkServerTime(strStartServerTime))
				||(!UserDataImpl.containsSessionId(strSessionId)));
	}

	private void prepareResponse(HttpServletResponse response){
		response.setContentType("text/html;charset=utf-8");
		response.setStatus(HttpServletResponse.SC_OK);
		response.setHeader("Cache-Control","no-store, no-cache, must-revalidate");
		response.setHeader("Expires", TimeHelper.getGMT());
	}

	public void handle(String target,Request baseRequest,
			HttpServletRequest request, HttpServletResponse response){
		prepareResponse(response);
		status stat=status.nothing;
		CookieDescriptor cookie=new CookieDescriptor(request.getCookies());
		String sessionId=cookie.getCookieByName("sessionId");
		String strStartServerTime=cookie.getCookieByName("startServerTime");
		UserDataSet userSession;
		baseRequest.setHandled(true);
		if(newUser(sessionId, strStartServerTime)){
			userSession=new UserDataSet();
			sessionId=SHA2.getSHA2(String.valueOf(frontendModel.creatorSessionId.incrementAndGet()));
			strStartServerTime=UserDataImpl.getStartServerTime();
			UserDataImpl.putSessionIdAndUserSession(sessionId, userSession);
		}
		else{
			stat=status.haveCookie;
			userSession=UserDataImpl.getUserSessionBySessionId(sessionId);
		}

		if(!inWeb(target)){
			if(!isStatic(target)){
				stat = status.notFound;
			}
		}
		
		userSession.visit();
		if (stat != status.notFound) {
			stat = getStatus(request, target, stat, sessionId);			
		}

		if (stat!=status.haveCookieAndPost){
			if (target.equals("/admin")) {
				stat = status.admin;
			}
			else if (target.equals("/rules")) {
				stat = status.rules;
			}
		}
		
		Status userStatus = null;
		
		switch(stat){
		case nothing:
			userStatus = new Nothing(frontendModel);
			break;
		case haveCookie:
			userStatus = new HaveCookie(frontendModel);
			break;
		case haveCookieAndPost:
			userStatus = new HaveCookieAndPost(frontendModel);
			break;
		case waiting:
			userStatus = new Waiting(frontendModel);
			break;
		case ready:
			userStatus = new Ready(frontendModel);
			break;
		case notFound:
			userStatus = new NotFound(frontendModel);
			break;
		case rules:
			userStatus = new Rules(frontendModel);
			break;
		case admin:
			userStatus = new Admin(frontendModel);
			break;
		}
		
		userStatus.exec(target, sessionId, userSession, request, response, strStartServerTime);
	}
}