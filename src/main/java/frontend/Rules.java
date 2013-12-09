package frontend;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import dbService.UserDataSet;

public class Rules extends Status {


    public Rules(FrontendModel frontendModel) {
        super(frontendModel);
    }

    @Override
    public void exec(String target, String sessionId, UserDataSet userSession,
                     HttpServletRequest request, HttpServletResponse response,
                     String strStartServerTime){
        sendPage("rules.html",userSession,response);
    }


}