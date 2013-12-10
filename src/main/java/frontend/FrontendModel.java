package frontend;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpServletResponse;

import utils.SysInfo;
import utils.TemplateHelper;
import dbService.UserDataSet;

import base.Abonent;
import base.Address;
import base.MessageSystem;

public class FrontendModel implements Abonent {
	
	private MessageSystem messageSystem;
	private Address address;
	
	public AtomicInteger creatorSessionId = new AtomicInteger();
	
	public Map<String,String> getStatistic(HttpServletResponse response, UserDataSet userSession){
		Map<String,String> data= new HashMap<String,String>();
		String mu=SysInfo.getStat("MemoryUsage");
		String tm = SysInfo.getStat("TotalMemory");
		String time=SysInfo.getStat("Time");
		String ccu = SysInfo.getStat("CCU");
		data.put("MemoryUsage", mu);
		data.put("Time", time);
		data.put("TotalMemory", tm);
		data.put("CCU", ccu);
		data.put("page", "admin.html");
		data.put("id", String.valueOf(userSession.getId()));
		data.put("nick", String.valueOf(userSession.getNick()));
		data.put("rating", String.valueOf(userSession.getRating()));
		
		return data;
	}

	
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
