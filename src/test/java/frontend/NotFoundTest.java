package frontend;

import static org.junit.Assert.*;

import messageSystem.MessageSystemImpl;

import org.junit.Test;
import static org.mockito.Mockito.*;

public class NotFoundTest {

	@Test
	public void test() {
		NotFound notFound = new NotFound(new FrontendModel(new MessageSystemImpl())),
				notFoundSpy = spy(notFound);
		doNothing().when(notFoundSpy).sendPage("404.html", null, null);
		notFoundSpy.exec(null, null, null, null, null, null);
		verify(notFoundSpy).sendPage("404.html", null, null);
	}

}
