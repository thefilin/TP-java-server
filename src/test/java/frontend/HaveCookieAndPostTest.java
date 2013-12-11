package frontend;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messageSystem.MessageSystemImpl;

import org.junit.Test;

import base.MessageSystem;

public class HaveCookieAndPostTest {
    @Test
    public void responseTest()
    {
        HttpServletRequest mockedRequest = mock(HttpServletRequest.class);
        HttpServletResponse mockedResponse = mock(HttpServletResponse.class);
        HaveCookieAndPost hcp = new HaveCookieAndPost(new FrontendModel(new MessageSystemImpl()));
        String nick=mockedRequest.getParameter("what");
        String password = mockedRequest.getParameter("islove");
        hcp.exec("/test", null, null,mockedRequest , mockedResponse, null);
        if ((nick==null)||(password==null)){
        verify(mockedResponse).setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
        verify(mockedResponse).addHeader("Location", "/wait");
        }
    }

}
