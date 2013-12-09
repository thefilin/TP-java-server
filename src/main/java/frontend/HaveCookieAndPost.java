package frontend;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.SHA2;
import base.Address;

import dbService.UserDataSet;

public class HaveCookieAndPost extends Status {

	public HaveCookieAndPost(FrontendModel frontendModel) {
		super(frontendModel);
	}

	@Override
	public void exec(String target, String sessionId, UserDataSet userSession,
			HttpServletRequest request, HttpServletResponse response,
			String strStartServerTime) {
		
		
		String nick=request.getParameter("nick");
		String password = request.getParameter("password");
		if ((nick==null)||(password==null)){
			nick = request.getParameter("regNick");
			password = request.getParameter("regPassword");
			if ((nick==null)||(password==null)||(nick.equals(""))||(password.equals(""))||(nick.length()>20)){
				sendPage(target+".html",userSession,response);
			}
			else{
				password=SHA2.getSHA2(password);
				response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
				response.addHeader("Location", "/wait");
				userSession.setPostStatus(1);
				
				frontendModel.addUser(sessionId, nick, password);
			}
		}
		else{
			password=SHA2.getSHA2(password);
			response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
			response.addHeader("Location", "/wait");
			userSession.setPostStatus(1);
			
			frontendModel.getUser(sessionId, nick, password);
		}
		
	}

}
