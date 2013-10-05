import base.Frontend;
import base.MessageSystem;
import frontend.FrontendImpl;
import messageSystem.MessageSystemImpl;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Response;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

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
        Response response = new Response(null, null);
        HttpServletRequest httpServletRequest = new HttpServletRequestWrapper(request);
        HttpServletResponse httpServletResponse = new HttpServletResponseWrapper(response);
        try{
            frontend.handle("/", request, httpServletRequest, httpServletResponse);
            assertTrue(true);
        } catch (Exception e){
            fail(e.getMessage());
        }
    }
}
