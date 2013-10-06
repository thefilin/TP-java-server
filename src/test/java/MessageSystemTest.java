import static org.junit.Assert.*;

import messageSystem.MessageSystemImpl;

import org.junit.Before;
import org.junit.Test;

import base.Abonent;
import base.Address;
import base.MessageSystem;
import base.Msg;

public class MessageSystemTest {
	private MessageSystem messageSystem;

	@Before
	public void setUp(){
		try{
		messageSystem = new MessageSystemImpl();
		}
		catch (Exception ignor){
		}
	}

	@Test
	public void testPutMsg() {
		FakeAbonent fakeAbonent = new FakeAbonent();
		Address addr = fakeAbonent.getAddress();

		messageSystem.addService(fakeAbonent, "fakeService");
		MsgToFakeAbonent msg = new MsgToFakeAbonent(null, addr);
		messageSystem.putMsg(addr, msg);
		messageSystem.execForAbonent(fakeAbonent);
		
		assertTrue(fakeAbonent.messageAccepted);
	}
	
	@Test
	public void testAddService() {
		Address correctAddress = new Address();
		FakeAbonent fakeAbonent = new FakeAbonent(correctAddress);
		messageSystem.addService(fakeAbonent, "fake");
		
		Address actualAddress = messageSystem.getAddressByName("fake");
		assertSame("Same addresses", correctAddress, actualAddress);
	}

    private class FakeAbonent implements Abonent {
        private Address address;
        public boolean messageAccepted;

        public FakeAbonent() {
            this.address = new Address();
        }

        public FakeAbonent(Address address) {
            this.address = address;
        }

        public Address getAddress() {
            return address;
        }
    }

    private class MsgToFakeAbonent extends Msg {

        public MsgToFakeAbonent(Address from, Address to) {
            super(from, to);
            assertEquals(from, getFrom());
            assertEquals(to, getTo());
        }

        @Override
        public void exec(Abonent abonent) {
            if (abonent instanceof FakeAbonent) {
                ((FakeAbonent) abonent).messageAccepted = true;
            }
        }
    }
}
