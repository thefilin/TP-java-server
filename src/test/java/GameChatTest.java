import base.GameChat;
import base.MessageSystem;
import chat.GameChatImpl;
import dbService.UserDataSet;
import frontend.UserDataImpl;
import junit.framework.Assert;
import messageSystem.MessageSystemImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Valery Ozhiganov
 * Date: 06.10.13
 * Time: 14:09
 * To change this template use File | Settings | File Templates.
 */
public class GameChatTest {
    private MessageSystem messageSystem;
    private GameChat gameChat;
    private String sessionId1, sessionId2;

    @Before
    public void before(){
        messageSystem = new MessageSystemImpl();
        gameChat = new GameChatImpl(messageSystem);
        sessionId1 = "1";
        sessionId2 = "2";
    }

    @Test
    public void getMessageSystemTest(){
        Assert.assertEquals(gameChat.getMessageSystem(), messageSystem);
    }

    @Test
    public void createChatTest(){
        gameChat.createChat(sessionId1, sessionId2);
    }

    @Test
    public void sendMessageTest(){
        gameChat.createChat(sessionId1, sessionId2);
        UserDataImpl.putLogInUser(sessionId1, new UserDataSet());
        GameChatImpl.sendMessage(sessionId1, "Hello, world!");
    }

}
