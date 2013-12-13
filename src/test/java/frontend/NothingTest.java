package frontend;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


import javax.servlet.http.HttpServletResponse;

import messageSystem.MessageSystemImpl;

import org.junit.Test;

import base.MessageSystem;

public class NothingTest {


    @Test
    public void responseTest() {
        HttpServletResponse mockedResponse = mock(HttpServletResponse.class);
        Nothing nothing = new Nothing(new FrontendModel(new MessageSystemImpl()));
        nothing.exec("/test", null, null, null, mockedResponse, null);

        verify(mockedResponse).setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
        verify(mockedResponse).addHeader("Location", "/");
    }
    
    @Test
    public void sendPageTest() {
        HttpServletResponse mockedResponse = mock(HttpServletResponse.class);
        Nothing nothing = new Nothing(new FrontendModel(new MessageSystemImpl()));
        Nothing spyNothing = spy(nothing);
        doNothing().when(spyNothing).sendPage("index.html", null, mockedResponse);
        spyNothing.exec("/", null, null, null, mockedResponse, null);
        verify(spyNothing).sendPage("index.html", null, mockedResponse);
    
    }
    
    
}