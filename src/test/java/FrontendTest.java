import base.Frontend;
import base.MessageSystem;
import frontend.FrontendImpl;
import messageSystem.MessageSystemImpl;

import javax.servlet.http.*;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created with IntelliJ IDEA.
 * User: valery
 * Date: 01.10.13
 * Time: 0:29
 * To change this template use File | Settings | File Templates.
 */
public class FrontendTest {
    private Frontend frontend;
    private MessageSystem messageSystem;

    @Before
    public void before() {
        messageSystem = new MessageSystemImpl();
        frontend = new FrontendImpl(messageSystem);
    }

    @Test
    public void test(){
        Request request = new Request(null, null);
        HttpServletRequest httpServletRequest = new HttpServletRequestWrapper(null);
        HttpServletResponse httpServletResponse = new HttpServletResponseWrapper(null);
        try{
            frontend.handle("/", request, httpServletRequest, httpServletResponse);
            assertTrue(true);
        } catch (Exception e){
            fail(e.getMessage());
        }
    }
}
