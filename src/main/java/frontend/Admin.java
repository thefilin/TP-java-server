package frontend;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.SysInfo;
import utils.TemplateHelper;

import dbService.UserDataSet;

public class Admin extends Status {

	public Admin(FrontendModel frontendModel) {
		super(frontendModel);
	}
	
	@Override
	public void exec(String target, String sessionId, UserDataSet userSession,
			HttpServletRequest request, HttpServletResponse response,
			String strStartServerTime) {
		
		Map<String,String> data = frontendModel.getStatistic(response, userSession);
		try {
			TemplateHelper.renderTemplate("template.html", data, response.getWriter());
		} catch (IOException ignor) {}
	}

}
