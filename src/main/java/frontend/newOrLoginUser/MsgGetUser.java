package frontend.newOrLoginUser;

import dbService.UserDataSet;
import base.Address;
import base.DataAccessObject;
import messageSystem.MsgToDBService;


public class MsgGetUser extends MsgToDBService{
	final public String login;
	final public String sessionId;
	final public String password;

	public MsgGetUser(Address from, Address to, String sessionId,String nick, String password){
		super(from,to);
		this.login=nick;
		this.sessionId=sessionId;
		this.password=password;
	}

	public void exec(DataAccessObject dbService){
		UserDataSet uds=dbService.getUDS(login, password);
		Address to=getFrom();
		MsgUpdateUser msg=new MsgUpdateUser(dbService.getAddress(),to,sessionId,uds);
		dbService.getMessageSystem().putMsg(to, msg);
	}
}