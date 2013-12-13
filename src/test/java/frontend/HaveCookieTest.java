package frontend;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


import javax.servlet.http.HttpServletResponse;

import messageSystem.MessageSystemImpl;

import org.junit.Test;

import dbService.UserDataSet;

import base.MessageSystem;

public class HaveCookieTest {

	
	@Test
	public void responseTest() {
		HttpServletResponse mockedResponse = mock(HttpServletResponse.class);
		HaveCookie hc = new HaveCookie(new FrontendModel(new MessageSystemImpl()));
		hc.exec("/test", null, null, null, mockedResponse, null);
		
		verify(mockedResponse).setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
		verify(mockedResponse).addHeader("Location", "/");
	}
	
	@Test
	public void regAndHomeTest() {
		
		HttpServletResponse mockedResponse = mock(HttpServletResponse.class);
		HaveCookie hc = new HaveCookie(new FrontendModel(new MessageSystemImpl()));
		HaveCookie spyHc = spy(hc);
		doNothing().when(spyHc).sendPage("reg.html", null, mockedResponse);
		doNothing().when(spyHc).sendPage("index.html", null, mockedResponse);
		spyHc.exec("/reg", null, null, null, mockedResponse, null);
		spyHc.exec("/", null, null, null, mockedResponse, null);
		verify(spyHc).sendPage("reg.html", null, mockedResponse);
		verify(spyHc).sendPage("index.html", null, mockedResponse);
	}	
}
