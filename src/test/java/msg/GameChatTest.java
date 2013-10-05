package msg;

import base.Address;
import base.GameChat;
import base.MessageSystem;
import gameMechanic.gameCreating.MsgCreateChat;
import messageSystem.MessageSystemImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: Valery Ozhiganov
 * Date: 05.10.13
 * Time: 22:51
 * To change this template use File | Settings | File Templates.
 */
public class GameChatTest {
    private MessageSystem messageSystem;
    private GameChatMock gameChat;

    @Before
    public void before(){
        messageSystem = new MessageSystemImpl();
        gameChat = new GameChatMock(messageSystem);
    }

    @Test
    public void testMsgCreateChat(){
        MsgCreateChat msg = new MsgCreateChat(null, null, null, null);

        assertFalse(gameChat.isUsedCreateChat());

        msg.exec(gameChat);

        assertTrue(gameChat.isUsedCreateChat());
    }

    private class GameChatMock implements GameChat {
        private MessageSystem messageSystem;
        private boolean usedCreateChat;

        public GameChatMock(MessageSystem messageSystem){
            this.messageSystem = messageSystem;
            usedCreateChat = false;
        }

        @Override
        public MessageSystem getMessageSystem() {
            return messageSystem;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void createChat(String sessionId1, String sessionId2) {
            //To change body of implemented methods use File | Settings | File Templates.
            usedCreateChat = true;
        }

        @Override
        public Address getAddress() {
            return null;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void run() {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        public boolean  isUsedCreateChat(){
            return usedCreateChat;
        }
    }
}
