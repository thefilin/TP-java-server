package frontend;

import static org.mockito.Mockito.*;

import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import messageSystem.MessageSystemImpl;

import org.junit.Test;

import base.MessageSystem;

public class AdminTest {

	@Test
	public void test() {
		FrontendModel frontendModel = new FrontendModel(new MessageSystemImpl()),
				spyFrontendModel = spy(frontendModel);
		HttpServletResponse mockedResponse = mock(HttpServletResponse.class);
		Admin admin = new Admin(spyFrontendModel);
		doReturn(new HashMap()).when(spyFrontendModel).getStatistic(mockedResponse, null);
		admin.exec(null, null, null, null, mockedResponse, null);
		verify(spyFrontendModel).getStatistic(mockedResponse, null);
		
		
	}

}
