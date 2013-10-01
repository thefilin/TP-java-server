import base.MessageSystem;
import dbService.UserDataSet;
import frontend.UserDataImpl;
import messageSystem.MessageSystemImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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

        UserDataImpl.putSessionIdAndUserSession(sessionId,user);
        assertTrue(UserDataImpl.containsSessionId(sessionId));
        assertEquals(UserDataImpl.getUserSessionBySessionId(sessionId), user);
        assertTrue(UserDataImpl.ccu()==1);
        assertTrue(UserDataImpl.getUserSessionBySessionId("lalala")==null);
        assertTrue(UserDataImpl.getLogInUserBySessionId("lallala")==null);
        assertTrue(UserDataImpl.getSessionIdByUserId(102020)==null);

        UserDataImpl.putLogInUser(sessionId, user);
        assertEquals(UserDataImpl.getLogInUserBySessionId(sessionId), user);
        assertEquals(UserDataImpl.getSessionIdByUserId(user.getId()), sessionId);
    }
/*
    @Test
    public void checkParty(){
        assertFalse(userData==null);

        UserDataSet user1 = new UserDataSet();
        UserDataSet user2 = new UserDataSet(1,"",1500,0,0);
        String sessionId1 = "1", sessionId2 = "2";

        UserDataImpl.putSessionIdAndUserSession(sessionId1, user1);
        UserDataImpl.putSessionIdAndUserSession(sessionId2, user2);
        userData.partyEnd(user1.getId(), user2.getId());
*//*
        assertTrue(user1.getWinQuantity()==1);
        assertTrue(user2.getLoseQuantity()==1);*//*

    }*/
}
