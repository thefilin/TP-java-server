package msg;

import base.Address;
import base.UserData;
import dbService.UserDataSet;
import frontend.newOrLoginUser.MsgUpdateUser;
import gameMechanic.Stroke.MsgPartyEnd;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Valery Ozhiganov
 * Date: 06.10.13
 * Time: 16:10
 * To change this template use File | Settings | File Templates.
 */
public class UserDataTest {
    private UserDataMock userData;

    @Before
    public void before(){
        userData = new UserDataMock();
    }

    @Test
    public void testMsgPartyEnd(){
        MsgPartyEnd msg = new MsgPartyEnd(null, null, 0, 0);

        Assert.assertFalse(userData.isUsedPartyEnd());

        msg.exec(userData);

        Assert.assertTrue(userData.isUsedPartyEnd());
    }

    @Test
    public void testMsgUpdateUser(){
        MsgUpdateUser msg = new MsgUpdateUser(null, null, null, null);

        Assert.assertFalse(userData.isUsedUpdateUserId());

        msg.exec(userData);

        Assert.assertTrue(userData.isUsedUpdateUserId());
    }


    private class  UserDataMock implements UserData {
        private boolean usedPartyEnd, usedUpdateUserId;

        public UserDataMock(){
            usedUpdateUserId = usedPartyEnd = false;
        }

        public boolean isUsedPartyEnd(){
            return usedPartyEnd;
        }

        public boolean isUsedUpdateUserId(){
            return usedUpdateUserId;
        }

        @Override
        public void updateUserId(String sessionId, UserDataSet user) {
            //To change body of implemented methods use File | Settings | File Templates.
            usedUpdateUserId = true;
        }

        @Override
        public void partyEnd(int winId, int loseId) {
            //To change body of implemented methods use File | Settings | File Templates.
            usedPartyEnd = true;
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
