package frontend;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.TemplateHelper;

import dbService.UserDataSet;

public abstract class Status {
	
	FrontendModel frontendModel;
	
	public Status(FrontendModel frontendModel) {
		this.frontendModel = frontendModel;
	}
	
	protected void sendPage(String name, UserDataSet userSession, HttpServletResponse response){
		try {
			Map<String, String> data = new HashMap<String, String>();
			data.put("page", name);
			if(userSession!=null){
				data.put("id", String.valueOf(userSession.getId()));
				data.put("nick", String.valueOf(userSession.getNick()));
				data.put("rating", String.valueOf(userSession.getRating()));
			}
			else{
				data.put("id", "0");
				data.put("nick", "Noname");
				data.put("rating", "500");
			}
			TemplateHelper.renderTemplate("template.html", data, response.getWriter());
		} 
		catch (IOException ignor) {
		}
	}
	
	public abstract void exec(String target, 
			String sessionId, 
			UserDataSet userSession, 
			HttpServletRequest request, 
			HttpServletResponse response, 
			String strStartServerTime);

}

