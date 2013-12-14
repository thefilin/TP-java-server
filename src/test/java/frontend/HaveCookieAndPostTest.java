package frontend;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messageSystem.MessageSystemImpl;

import org.junit.Test;

import dbService.UserDataSet;

import base.MessageSystem;

public class HaveCookieAndPostTest {
	
    @Test
    public void userRequestTest()
    {
        HttpServletRequest mockedRequest = mock(HttpServletRequest.class);
        HttpServletResponse mockedResponse = mock(HttpServletResponse.class);
        UserDataSet mockedUserSession = mock(UserDataSet.class);
        HaveCookieAndPost hcp = new HaveCookieAndPost(mock(FrontendModel.class));
        
        when(mockedRequest.getParameter("nick")).thenReturn("testNick");
        when(mockedRequest.getParameter("password")).thenReturn("testPass");
        hcp.exec("/test", null, mockedUserSession,mockedRequest , mockedResponse, null);
        verify(mockedResponse).setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
        verify(mockedResponse).addHeader("Location", "/wait");
    }
    
    @Test
    public void noUserRequestTest()
    {
        HttpServletRequest mockedRequest = mock(HttpServletRequest.class);
        HttpServletResponse mockedResponse = mock(HttpServletResponse.class);
        UserDataSet mockedUserSession = mock(UserDataSet.class);
        HaveCookieAndPost hcp = new HaveCookieAndPost(mock(FrontendModel.class));
        
        when(mockedRequest.getParameter("regNick")).thenReturn("testNick");
        when(mockedRequest.getParameter("regPassword")).thenReturn("testPass");
        hcp.exec("/test", null, mockedUserSession,mockedRequest , mockedResponse, null);
        verify(mockedResponse).setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
        verify(mockedResponse).addHeader("Location", "/wait");
    }
    
    @Test
    public void emptyUserTest()
    {
        HttpServletRequest mockedRequest = mock(HttpServletRequest.class);
        HttpServletResponse mockedResponse = mock(HttpServletResponse.class);
        UserDataSet mockedUserSession = mock(UserDataSet.class);
        HaveCookieAndPost hcp = new HaveCookieAndPost(mock(FrontendModel.class)),
        		hcpSpy = spy(hcp);
        
        doNothing().when(hcpSpy).sendPage("/test.html", null, mockedResponse);
        when(mockedRequest.getParameter("regNick")).thenReturn("");
        when(mockedRequest.getParameter("regPassword")).thenReturn("");
        
        hcpSpy.exec("/test", null, mockedUserSession,mockedRequest , mockedResponse, null);
        verify(hcpSpy).sendPage("/test.html", mockedUserSession, mockedResponse);
    }
    
    

}
