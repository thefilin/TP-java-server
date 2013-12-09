package frontend;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbService.UserDataSet;

public class HaveCookie extends Status {

	public HaveCookie(FrontendModel frontendModel) {
		super(frontendModel);
	}

	@Override
	public void exec(String target, String sessionId, UserDataSet userSession,
			HttpServletRequest request, HttpServletResponse response,
			String strStartServerTime) {
		
		if (target.equals("/")){
			sendPage("index.html",userSession,response);
		}
		else if (target.equals("/reg")){
			sendPage("reg.html",userSession,response);
		}
		else{
			response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
			response.addHeader("Location", "/");
		}
		
		
	}
	

}
