package frontend;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbService.UserDataSet;

public class Nothing extends Status {

	public Nothing(FrontendModel frontendModel) {
		super(frontendModel);
	}

	@Override
	public void exec(String target, String sessionId, UserDataSet userSession,
			HttpServletRequest request, HttpServletResponse response,
			String strStartServerTime) {
		
		boolean moved=false;
		if (!target.equals("/")){
			response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
			response.addHeader("Location", "/");
			moved=true;
		}
		Cookie cookie1=new Cookie("sessionId", sessionId);
		Cookie cookie2=new Cookie("startServerTime",strStartServerTime);
		response.addCookie(cookie1);
		response.addCookie(cookie2);
		if (!moved){
			sendPage("index.html",userSession,response);
		}
	
		
	}
	

}
