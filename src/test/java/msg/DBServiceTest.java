package msg;

import base.Address;
import base.DataAccessObject;
import base.MessageSystem;
import dbService.UserDataSet;
import frontend.newOrLoginUser.MsgAddUser;
import frontend.newOrLoginUser.MsgGetUser;
import junit.framework.Assert;
import messageSystem.MessageSystemImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Valery Ozhiganov
 * Date: 04.10.13
 * Time: 1:00
 * To change this template use File | Settings | File Templates.
 */
public class DBServiceTest {
    private DataAccessObjectMock dao;

    @Before
    public void before(){
        MessageSystem messageSystem = new MessageSystemImpl();
        dao = new DataAccessObjectMock(messageSystem);
    }

    @Test
    public void testMsgAddUser(){
        MsgAddUser msgAddUser = new MsgAddUser(dao.getAddress(),dao.getAddress(),"1","Valera","Valera123");
        msgAddUser.exec(dao);
        Assert.assertTrue(dao.getMessageSystem().getMessages().size()==1);
    }

    @Test
    public void testMsgGetUser(){
        MsgGetUser msgGetUser = new MsgGetUser(null,dao.getAddress(),"1", "Valera", "Valera123");
        msgGetUser.exec(dao);
        Assert.assertTrue(dao.getMessageSystem().getMessages().size()==1);
    }

    private class DataAccessObjectMock implements DataAccessObject {
        private final MessageSystem messageSystem;
        private String login, password;

        public DataAccessObjectMock(MessageSystem messageSystem){
            this.messageSystem = messageSystem;
            messageSystem.addService(this, "DataAccessObject");
        }

        @Override
        public MessageSystem getMessageSystem() {
            return messageSystem;
        }

        @Override
        public UserDataSet getUDS(String login, String password) {
            return new UserDataSet();  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public boolean addUDS(String login, String password) {
            this.login = login; this.password = password;
            return true;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void updateUsers(List<UserDataSet> users) {
            //To change body of implemented methods use File | Settings | File Templates.
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
