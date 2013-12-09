package frontend;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbService.UserDataSet;

public class Waiting extends Status {

	public Waiting(FrontendModel frontendModel) {
		super(frontendModel);
	}

	@Override
	public void exec(String target, String sessionId, UserDataSet userSession,
			HttpServletRequest request, HttpServletResponse response,
			String strStartServerTime) {
		
		sendPage("wait.html",null,response);
		
	}
	

}
