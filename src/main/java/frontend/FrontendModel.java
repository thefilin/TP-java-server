package frontend;

import java.util.concurrent.atomic.AtomicInteger;

import base.Abonent;
import base.Address;
import base.MessageSystem;

public class FrontendModel implements Abonent {
	
	private MessageSystem messageSystem;
	private Address address;
	
	public AtomicInteger creatorSessionId = new AtomicInteger();
	
	public FrontendModel(MessageSystem msgSystem){
		address=new Address();
		messageSystem=msgSystem;
		messageSystem.addService(this,"Frontend");
	}
	
	public void addUser(String sessionId, String nick, String password) {
		Address to=messageSystem.getAddressByName("DBService");
		Address from=messageSystem.getAddressByName("UserData");
		MsgAddUser msg=new MsgAddUser(from,to,sessionId,nick,password);
		messageSystem.putMsg(to, msg);
	}
	
	public void getUser(String sessionId, String nick, String password) {
		Address to=messageSystem.getAddressByName("DBService");
		Address from=messageSystem.getAddressByName("UserData");
		MsgGetUser msg=new MsgGetUser(from,to,sessionId,nick,password);
		messageSystem.putMsg(to, msg);
	}

	public Address getAddress(){
		return address;
	}

}
