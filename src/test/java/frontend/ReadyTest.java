package frontend;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import messageSystem.MessageSystemImpl;

import org.junit.Test;

import dbService.UserDataSet;

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
    
    @Test
    public void homeTest() {
    		HttpServletResponse mockedResponse = mock(HttpServletResponse.class);
        Ready rd = new Ready(new FrontendModel(new MessageSystemImpl())),
        		spyRd = spy(rd);
        doNothing().when(spyRd).sendPage("index.html", null, mockedResponse);
        UserDataSet fakeSession = new UserDataSet();
        spyRd.exec("/", "", fakeSession, null, mockedResponse, null);
        verify(spyRd).sendPage("index.html", fakeSession, mockedResponse);
    }
    
    @Test
    public void profileTest() {
    		HttpServletResponse mockedResponse = mock(HttpServletResponse.class);
        Ready rd = new Ready(new FrontendModel(new MessageSystemImpl())),
        		spyRd = spy(rd);
        doNothing().when(spyRd).sendPage("profile.html", null, mockedResponse);
        UserDataSet fakeSession = new UserDataSet();
        spyRd.exec("/profile", "", fakeSession, null, mockedResponse, null);
        verify(spyRd).sendPage("profile.html", fakeSession, mockedResponse);
    }
    
    @Test
    public void gameTest() {
    		HttpServletResponse mockedResponse = mock(HttpServletResponse.class);
        Ready rd = new Ready(new FrontendModel(new MessageSystemImpl())),
        		spyRd = spy(rd);
        doNothing().when(spyRd).sendPage("game.html", null, mockedResponse);
        UserDataSet fakeSession = new UserDataSet();
        spyRd.exec("/game", "", fakeSession, null, mockedResponse, null);
        verify(spyRd).sendPage("game.html", fakeSession, mockedResponse);
    }
    
}