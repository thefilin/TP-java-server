import base.Abonent;
import base.Address;
import base.MessageSystem;
import chat.ChatWSImpl;
import dbService.UserDataSet;
import frontend.UserDataImpl;
import frontend.WebSocketImpl;
import messageSystem.MessageSystemImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: Valery Ozhiganov
 * Date: 01.10.13
 * Time: 10:57
 * To change this template use File | Settings | File Templates.
 */
public class UserDataImplTest {
    private MessageSystem messageSystem;
    private UserDataImpl userData;

    @Before
    public void before(){
        messageSystem = new MessageSystemImpl();
        userData = new UserDataImpl(messageSystem);
    }

    @Test
    public void checkGetAndSet(){
        assertTrue(UserDataImpl.checkServerTime(UserDataImpl.getStartServerTime()));
    }

    @Test
    public void checkUsers(){
        UserDataSet user = new UserDataSet();
        String sessionId = "12345";

        UserDataImpl.putSessionIdAndUserSession(sessionId, user);
        assertTrue(UserDataImpl.containsSessionId(sessionId));
        assertEquals(UserDataImpl.getUserSessionBySessionId(sessionId), user);
        assertTrue(UserDataImpl.getUserSessionBySessionId("lalala")==null);
        assertTrue(UserDataImpl.getLogInUserBySessionId("lallala")==null);
        assertTrue(UserDataImpl.getSessionIdByUserId(102020)==null);

        UserDataImpl.putLogInUser(sessionId, user);
        assertEquals(UserDataImpl.getLogInUserBySessionId(sessionId), user);
        //assertEquals(UserDataImpl.getSessionIdByUserId(user.getId()), sessionId);
    }

    @Test
    public void checkParty(){
        final Address address = new Address();
        Abonent abonent = new Abonent() {
            @Override
            public Address getAddress() {
                return address;  //To change body of implemented methods use File | Settings | File Templates.
            }
        };
        messageSystem.addService(abonent, "DBService");

        UserDataSet user1 = new UserDataSet();
        UserDataSet user2 = new UserDataSet(1,"",1500,0,0);
        String sessionId1 = "1", sessionId2 = "2";

        UserDataImpl.putSessionIdAndUserSession(sessionId1, user1);
        UserDataImpl.putSessionIdAndUserSession(sessionId2, user2);

        UserDataImpl.putLogInUser(sessionId1, user1);
        UserDataImpl.putLogInUser(sessionId2, user2);

        UserDataImpl.playerWantToPlay(sessionId1, user1);
        UserDataImpl.playerWantToPlay(sessionId2, user2);

        userData.partyEnd(user1.getId(), user2.getId());

        assertTrue(user1.getWinQuantity()==1);
        assertTrue(user2.getLoseQuantity()==1);

    }

    @Test
    public void updateUserIdTest(){
        UserDataSet user = new UserDataSet(15, null, 0, 0, 0);
        String sessionId = "15";
        UserDataImpl.putSessionIdAndUserSession(sessionId, user);
        UserDataImpl.putLogInUser(sessionId, user);

        user = new UserDataSet(155, null, 0, 0, 0);

        userData.updateUserId(sessionId, user);

        assertTrue(UserDataImpl.getLogInUserBySessionId(sessionId).getId()==155);
    }

    @Test
    public void webSocketsTest(){
        String sessionId = "1938";

        assertNull(UserDataImpl.getWSBySessionId(sessionId));

        WebSocketImpl webSocket = new WebSocketImpl(false);

        UserDataImpl.putLogInUser(sessionId, new UserDataSet(1938, null, 0, 0 , 0));
        UserDataImpl.putSessionIdAndWS(sessionId, webSocket);

//        assertEquals(UserDataImpl.getWSBySessionId(sessionId), webSocket.getSession().getRemote());
    }
}
