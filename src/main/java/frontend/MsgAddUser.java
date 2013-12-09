package frontend;

import dbService.UserDataSet;
import base.Address;
import base.DataAccessObject;
import messageSystem.MsgToDBService;


public class MsgAddUser extends MsgToDBService{
	final public String login;
	final public String sessionId;
	final public String password;

	public MsgAddUser(Address from, Address to, String sessionId,String nick, String password){
		super(from,to);
		this.login=nick;
		this.sessionId=sessionId;
		this.password=password;
	}

	public void exec(DataAccessObject dbService){
		UserDataSet uds=null;
		if (dbService.addUDS(login, password)){
			uds=dbService.getUDS(login, password);
		}
		Address to=getFrom();
		MsgUpdateUser msg=new MsgUpdateUser(dbService.getAddress(),to,sessionId,uds);
		dbService.getMessageSystem().putMsg(to, msg);
	}
}