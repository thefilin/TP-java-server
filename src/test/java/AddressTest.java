import static org.junit.Assert.*;

import org.junit.Test;

import base.Address;

public class AddressTest {
	
	@Test
	public void test() {
		Address address1 = new Address();
		Address address2 = new Address();	
		assertEquals("addr2 = addr1 + 1", address2.getAbonentId(), address1.getAbonentId() + 1);
	}
}
