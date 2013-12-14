package frontend;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import messageSystem.MessageSystemImpl;

import org.junit.Test;

public class WaitingTest {

	@Test
	public void test() {
		Waiting waiting = new Waiting(new FrontendModel(new MessageSystemImpl())),
				waitingSpy = spy(waiting);
		doNothing().when(waitingSpy).sendPage("wait.html", null, null);
		waitingSpy.exec(null, null, null, null, null, null);
		verify(waitingSpy).sendPage("wait.html", null, null);
	}

}
