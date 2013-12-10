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

	enum status {nothing,haveCookie,haveCookieAndPost,waiting,ready}

	public FrontendImpl(MessageSystem msgSystem){
		frontendModel = new FrontendModel(msgSystem);
	}


	private void getStatistic(HttpServletResponse response, UserDataSet userSession){
		Map<String,String> data= new HashMap<String,String>();
		String mu=SysInfo.getStat("MemoryUsage");
		String tm = SysInfo.getStat("TotalMemory");
		String time=SysInfo.getStat("Time");
		String ccu = SysInfo.getStat("CCU");
		data.put("MemoryUsage", mu);
		data.put("Time", time);
		data.put("TotalMemory", tm);
		data.put("CCU", ccu);
		data.put("page", "admin.html");
		data.put("id", String.valueOf(userSession.getId()));
		data.put("nick", String.valueOf(userSession.getNick()));
		data.put("rating", String.valueOf(userSession.getRating()));
		try {
			TemplateHelper.renderTemplate("template.html", data, response.getWriter());
		} catch (IOException ignor) {
		}
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

	private void prepareResponse(HttpServletResponse response){
		response.setContentType("text/html;charset=utf-8");
		response.setStatus(HttpServletResponse.SC_OK);
		response.setHeader("Cache-Control","no-store, no-cache, must-revalidate");
		response.setHeader("Expires", TimeHelper.getGMT());
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

        Status pageStatus;
		if(!inWeb(target)){
			if(!isStatic(target)){
            pageStatus = new NotFound(frontendModel) ;
            pageStatus.exec(target, sessionId, userSession, request, response, strStartServerTime);
			}
			return;	
		}
		userSession.visit();
		stat=getStatus(request, target, stat, sessionId);
		if (stat!=status.haveCookieAndPost){
			if(target.equals("/admin")){
				getStatistic(response,userSession);
				return;
			}
			else if (target.equals("/rules")){
                pageStatus = new Rules(frontendModel) ;
                pageStatus.exec(target, sessionId, userSession, request, response, strStartServerTime);
				return;
			}
		}
		
		Status userStatus=null;
		
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
		}
		
		userStatus.exec(target, sessionId, userSession, request, response, strStartServerTime);
	}
}