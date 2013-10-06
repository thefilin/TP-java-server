package msg;

import base.Abonent;
import base.Address;
import base.GameMechanic;
import base.MessageSystem;
import dbService.UserDataSet;
import frontend.MsgRemoveUserFromGM;
import gameClasses.Snapshot;
import gameClasses.Stroke;
import gameMechanic.Stroke.MsgCheckStroke;
import gameMechanic.gameCreating.MsgCreateGames;
import junit.framework.Assert;
import messageSystem.MessageSystemImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;
/**
 * Created with IntelliJ IDEA.
 * User: Valery Ozhiganov
 * Date: 04.10.13
 * Time: 1:48
 * To change this template use File | Settings | File Templates.
 */
public class GameMechanicTest {
    private GameMechanicMock gameMechanic;
    private MessageSystem messageSystem;
    @Before
    public void before(){
        messageSystem = new MessageSystemImpl();
        gameMechanic = new GameMechanicMock(messageSystem);
    }

    @Test
    public void testMsgCheckStroke(){
        final Address wsAddress = new Address();
        Abonent ws = new Abonent() {
            @Override
            public Address getAddress() {
                return wsAddress;  //To change body of implemented methods use File | Settings | File Templates.
            }
        };
        messageSystem.addService(ws, "WebSocket");

        MsgCheckStroke msg = new MsgCheckStroke(gameMechanic.getAddress(), gameMechanic.getAddress(),0,new Stroke());

        assertFalse(gameMechanic.isUsedCheckStroke());

        msg.exec(gameMechanic);

        assertTrue(gameMechanic.isUsedCheckStroke());
    }

    @Test
    public void testMsgRemoveUserFromGM(){
        MsgRemoveUserFromGM msg = new MsgRemoveUserFromGM(null, null, null);

        assertFalse(gameMechanic.isUsedRemoveUser());

        msg.exec(gameMechanic);

        assertTrue(gameMechanic.isUsedRemoveUser());
    }

    @Test
    public void testMsgCreateGames(){
        final Address wsAddress = new Address();
        Abonent ws = new Abonent() {
            @Override
            public Address getAddress() {
                return wsAddress;  //To change body of implemented methods use File | Settings | File Templates.
            }
        };
        messageSystem.addService(ws, "WebSocket");

        MsgCreateGames msg = new MsgCreateGames(gameMechanic.getAddress(), wsAddress, null);
        msg.exec(gameMechanic);

        Assert.assertTrue(gameMechanic.isUsedCreateGames());
        Assert.assertTrue(messageSystem.getMessages().get(wsAddress).size()==1);
    }




    private class GameMechanicMock implements GameMechanic {
        private MessageSystem messageSystem;
        private Address address;
        private boolean usedRemoveUser, usedCheckStroke, usedCreateGames;

        public GameMechanicMock(MessageSystem messageSystem){
            this.address = new Address();
            this.messageSystem = messageSystem;
            usedCreateGames = usedCheckStroke = usedRemoveUser = false;
        }

        public boolean isUsedRemoveUser(){
            return usedRemoveUser;
        }

        public boolean isUsedCheckStroke(){
            return usedCheckStroke;
        }

        public boolean isUsedCreateGames(){
            return usedCreateGames;
        }

        @Override
        public Map<String, String> createGames(Map<String, UserDataSet> users) {
            usedCreateGames = true;
            return null;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public Map<Integer, Stroke> checkStroke(int id, Stroke stroke) {
            usedCheckStroke = true;
            return null;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void removeUser(String sessionId) {
            //To change body of implemented methods use File | Settings | File Templates.
            usedRemoveUser = true;
        }

        @Override
        public Snapshot getSnapshot(int id) {
            return null;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public MessageSystem getMessageSystem() {
            return messageSystem;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public Address getAddress() {
            return address;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void run() {
            //To change body of implemented methods use File | Settings | File Templates.
        }
    }
}
