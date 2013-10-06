package msg;

import base.Address;
import base.MessageSystem;
import base.WebSocket;
import frontend.WebSocketImpl;
import gameClasses.Snapshot;
import gameClasses.Stroke;
import gameMechanic.Stroke.MsgDoneSnapshot;
import gameMechanic.Stroke.MsgDoneStroke;
import gameMechanic.gameCreating.MsgUpdateColors;
import junit.framework.Assert;
import messageSystem.MessageSystemImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Valery Ozhiganov
 * Date: 06.10.13
 * Time: 15:57
 * To change this template use File | Settings | File Templates.
 */
public class WebSocketTest {
    private WebSocketMock webSocket;

    @Before
    public void before(){
        webSocket = new WebSocketMock();
    }

    @Test
    public void testMsgUpdateColors(){
        MsgUpdateColors msg = new MsgUpdateColors(null, null, null);

        Assert.assertFalse(webSocket.isUsedUpdateUsersColor());

        msg.exec(webSocket);

        Assert.assertTrue(webSocket.isUsedUpdateUsersColor());
    }

    @Test
    public void testMsgDoneSnapshot(){
        MsgDoneSnapshot msg = new MsgDoneSnapshot(null, null, 9, null);

        Assert.assertFalse(webSocket.isUsedDoneSnapshot());

        msg.exec(webSocket);

        Assert.assertTrue(webSocket.isUsedDoneSnapshot());
    }

    @Test
    public void testMsgDoneStroke(){
        MsgDoneStroke msg = new MsgDoneStroke(null, null, null);

        Assert.assertFalse(webSocket.isUsedSendStroke());

        msg.exec(webSocket);

        Assert.assertTrue(webSocket.isUsedSendStroke());
    }

    private class WebSocketMock implements WebSocket{
        private boolean usedUpdateUsersColor, usedDoneSnapshot, usedSendStroke;

        public WebSocketMock(){
            usedSendStroke = usedDoneSnapshot = usedUpdateUsersColor = false;
        }

        public boolean isUsedUpdateUsersColor(){
            return usedUpdateUsersColor;
        }

        public boolean isUsedDoneSnapshot(){
            return usedDoneSnapshot;
        }

        public boolean isUsedSendStroke(){
            return usedSendStroke;
        }

        @Override
        public void sendStroke(Map<Integer, Stroke> userIdToStroke) {
            //To change body of implemented methods use File | Settings | File Templates.
            usedSendStroke = true;
        }

        @Override
        public void doneSnapshot(int id, Snapshot snapshot) {
            //To change body of implemented methods use File | Settings | File Templates.
            usedDoneSnapshot = true;
        }

        @Override
        public void updateUsersColor(Map<String, String> usersToColors) {
            //To change body of implemented methods use File | Settings | File Templates.
            usedUpdateUsersColor = true;
        }

        @Override
        public Address getAddress() {
            return null;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void run() {
            //To change body of implemented methods use File | Settings | File Templates.
        }
    }
}
