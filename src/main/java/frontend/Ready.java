package frontend;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.SHA2;

import dbService.UserDataSet;

public class Ready extends Status {

	public Ready(FrontendModel frontendModel) {
		super(frontendModel);
	}

	@Override
	public void exec(String target, String sessionId, UserDataSet userSession,
			HttpServletRequest request, HttpServletResponse response,
			String strStartServerTime) {

		if(target.equals("/")){
			UserDataImpl.putLogInUser(sessionId, userSession);
			sendPage("index.html",userSession,response);			
		}
		else if (target.equals("/game")){
			UserDataImpl.putLogInUser(sessionId, userSession);
			UserDataImpl.playerWantToPlay(sessionId, userSession);
			sendPage("game.html",userSession,response);
		}
		else if(target.equals("/logout")){
			response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
			response.addHeader("Location", "/");
			String strSessionId = sessionId=SHA2.getSHA2(String.valueOf(frontendModel.creatorSessionId.incrementAndGet()));
			Cookie cookie=new Cookie("sessionId", strSessionId);
			response.addCookie(cookie);
			UserDataImpl.putSessionIdAndUserSession(sessionId, new UserDataSet());
		}
		else if (target.equals("/profile")){
			sendPage("profile.html",userSession,response);
		}
		else{
			response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
			response.addHeader("Location", "/");
		}
		
	}
	
	

}
