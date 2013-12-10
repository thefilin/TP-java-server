package frontend.newOrLoginUser;

import dbService.UserDataSet;
import base.Address;
import base.UserData;
import messageSystem.MsgToUserData;


public class MsgUpdateUser extends MsgToUserData{
	final private String sessionId;
	final private UserDataSet uds;

	public MsgUpdateUser(Address from, Address to, String sessionId, UserDataSet uds){
		super(from,to);
		this.sessionId=sessionId;
		this.uds=uds;
	}

	public void exec(UserData userData){
		userData.updateUserId(sessionId, uds);
	}
}