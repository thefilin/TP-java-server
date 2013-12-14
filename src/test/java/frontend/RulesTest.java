package frontend;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import messageSystem.MessageSystemImpl;

import org.junit.Test;

public class RulesTest {

	@Test
	public void test() {
		Rules rules = new Rules(new FrontendModel(new MessageSystemImpl())),
				rulesSpy = spy(rules);
		doNothing().when(rulesSpy).sendPage("rules.html", null, null);
		rulesSpy.exec(null, null, null, null, null, null);
		verify(rulesSpy).sendPage("rules.html", null, null);
	}

}
