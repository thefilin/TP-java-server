import chat.ChatMessage;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: valery
 * Date: 01.10.13
 * Time: 0:18
 * To change this template use File | Settings | File Templates.
 */
public class ChatMessageTest {
    private String sender, text;
    private ChatMessage chatMessage;

    @Before
    public void before(){
        sender = "Valera";
        text = "Hello world!";
        chatMessage = new ChatMessage(sender, text);
    }

    @Test
    public void checkSender(){
        assertEquals(sender, chatMessage.getSender());
    }

    @Test
    public void checkText(){
        assertEquals(text, chatMessage.getMessage());
    }

    @Test
    public void checkJSON(){
        String json = ("{\"sender\":\""+sender+"\",\"text\":\""+text+"\"}");
        assertEquals(json, chatMessage.json());
    }
}
