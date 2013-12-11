package frontend;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import messageSystem.MessageSystemImpl;

import org.junit.Test;

import base.MessageSystem;
import utils.SHA2;

public class ReadyTest {


    @Test
    public void responseTest() {
        HttpServletResponse mockedResponse = mock(HttpServletResponse.class);
        Ready rd = new Ready(new FrontendModel(new MessageSystemImpl()));
        rd.exec("/test", null, null, null, mockedResponse, null);

        verify(mockedResponse).setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
        verify(mockedResponse).addHeader("Location", "/");
    }

    @Test
    public void responseTest_2nd() {
    HttpServletResponse mockedResponse = mock(HttpServletResponse.class);
    Ready rd = new Ready(new FrontendModel(new MessageSystemImpl()));
    rd.exec("/logout", null, null, null, mockedResponse, null);
        verify(mockedResponse).setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
        verify(mockedResponse).addHeader("Location", "/");
    }
}