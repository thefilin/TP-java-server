import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import messageSystem.MessageSystemImpl;

import org.hibernate.cfg.Configuration;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InOrder;

import base.MessageSystem;
import dbService.DBServiceHibernateImpl;
import dbService.UserDataSet;


public class DBServiceHibernateTest {
        
        static MessageSystem msgSystem;
        
        @BeforeClass
        public static void InitMsgSystem() {
        		msgSystem = new MessageSystemImpl();
        }
        
        @Test
        public void CreateServiceHibernateTest() {
        	
        		DBServiceHibernateImpl db = new DBServiceHibernateImpl(msgSystem, new Configuration());
        		Assert.assertEquals(msgSystem, db.getMessageSystem());
        }
        
        @Test
        public void ConfigTest() {
        	
        		Configuration mockConf = mock(Configuration.class);
        		when(mockConf.getProperties()).thenReturn(null);
        		
        		DBServiceHibernateImpl db = new DBServiceHibernateImpl(msgSystem, mockConf);
        		
        		InOrder inOrder = inOrder(mockConf);
        		inOrder.verify(mockConf).setProperty("hibernate.connection.username", "checkers");
        	
        }
        
        @Test
        public void getUDSTest() {
	        	Configuration mockConf = mock(Configuration.class);
	    		when(mockConf.getProperties()).thenReturn(null);
	    		
	    		DBServiceHibernateImpl db = new DBServiceHibernateImpl(msgSystem, mockConf);
	    		
	    		Assert.assertEquals(db.getUDS("aaa", "bbb"), null);
        }
        
        @Test
        public void addUDSTest() {
	        	Configuration mockConf = mock(Configuration.class);
	    		when(mockConf.getProperties()).thenReturn(null);
	    		
	    		DBServiceHibernateImpl db = new DBServiceHibernateImpl(msgSystem, mockConf);
	    		
	    		boolean thrown = false;
	    		try {
	    			db.addUDS("aaa", "bbb");
	    		}
	    		catch (NullPointerException e) {
	    			thrown = true;
	    		}
	    		
	    		
	    		Assert.assertEquals(thrown, true);
        }
        
        @Test
        public void emptyListTest() {
        		List<UserDataSet> fakeList = new ArrayList<UserDataSet>();
        		fakeList.add(new UserDataSet());
        		fakeList.add(new UserDataSet());
        		fakeList.add(new UserDataSet());
        		
        		List spyList = spy(fakeList);
        		
        		Configuration mockConf = mock(Configuration.class);
        		when(mockConf.getProperties()).thenReturn(null);
        		
        		
        		
        		DBServiceHibernateImpl db = new DBServiceHibernateImpl(msgSystem, mockConf);
        		db.updateUsers(spyList);
        		
        		verify(spyList).listIterator();      		
        }
        
}