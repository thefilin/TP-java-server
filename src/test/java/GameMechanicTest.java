import base.GameMechanic;
import base.MessageSystem;
import base.Abonent;
import base.Address;
import dbService.UserDataSet;
import gameMechanic.GameMechanicImpl;
import messageSystem.MessageSystemImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: valery
 * Date: 01.10.13
 * Time: 1:41
 * To change this template use File | Settings | File Templates.
 */
public class GameMechanicTest {
    private GameMechanic gameMechanic;
    private MessageSystem messageSystem;

    @Before
    public void before(){
        messageSystem = new MessageSystemImpl();
        gameMechanic = new GameMechanicImpl(messageSystem);
    }

    @Test
    public void test(){
        assertEquals(gameMechanic.getMessageSystem(), messageSystem);
    }

    @Test
    public void testGameCreating(){
        final Address chatAddress = new Address();
	Abonent chat = new Abonent(){
		@Override
		public Address getAddress(){
			return chatAddress;
		}
	};
	messageSystem.addService(chat, "GameChat");
	Map<String, String> resp;
        UserDataSet user0 = new UserDataSet(0,"First",0,0,0);
        UserDataSet user1 = new UserDataSet(1,"Second",0,0,0);
        Map<String, UserDataSet> users= new HashMap<String, UserDataSet>();
        resp = gameMechanic.createGames(users);
        assertTrue(resp.size()==0);
        users.put("0", user0);
        users.put("1", user1);
        resp = gameMechanic.createGames(users);
        assertTrue(resp.size()==2);
        assertTrue(resp.containsKey("0"));
        assertTrue(resp.containsKey("1"));
    }

}
