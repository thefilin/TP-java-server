import base.Abonent;
import base.Address;
import base.MessageSystem;
import base.WebSocket;
import dbService.UserDataSet;
import frontend.UserDataImpl;
import frontend.WebSocketImpl;
import messageSystem.MessageSystemImpl;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Valery Ozhiganov
 * Date: 06.10.13
 * Time: 2:30
 * To change this template use File | Settings | File Templates.
 */
public class WebSocketTest {
    private MessageSystem messageSystem;
    private WebSocketImpl webSocket;

    @Before
    public void before(){
        messageSystem = new MessageSystemImpl();
        WebSocketImpl.setMS(messageSystem);
        webSocket = new WebSocketImpl(true);
    }

    @Test
    public void testEmptyConstructor(){
        WebSocket webSocket1 = new WebSocketImpl();
        Assert.assertNull(webSocket1.getAddress());
    }

    @Test
    public void onWebSocketTextTest(){
        UserDataSet user1 = new UserDataSet();
        UserDataSet user2 = new UserDataSet();
        String sst = UserDataImpl.getStartServerTime();

        UserDataImpl.putLogInUser("1", user1);

        webSocket.onWebSocketText("lala");
        webSocket.onWebSocketText("{\"sessionId\":\"1\", \"startServerTime\":\""+sst+"\"}", true);
        webSocket.onWebSocketText("{\"sessionId\":\"2\", \"startServerTime\":\""+sst+"\"}", true);
    }
/*

    @Test
    public void checkStrokeTest(){
        final Address address = new Address();
        Abonent gm = new Abonent() {
            @Override
            public Address getAddress() {
                return address;  //To change body of implemented methods use File | Settings | File Templates.
            }
        };

        messageSystem.addService(gm, "GameMechanic");

        JSONObject json = new JSONObject();
        json.put("sessionId", 1);
        json.put("startServerTime", UserDataImpl.getStartServerTime());
        json.put("from_x", 0);
        json.put("from_y", 0);
        json.put("to_x", 0);
        json.put("to_y", 0);
        json.put("status", "status");
        webSocket.onWebSocketText(json.toJSONString(), true);

        Assert.assertEquals(messageSystem.getMessages().get(gm.getAddress()).size(), 1);
    }
*/

}
