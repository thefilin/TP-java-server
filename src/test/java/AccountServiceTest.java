import accountService.AccountServiceImpl;
import base.AccountService;
import base.MessageSystem;
import messageSystem.MessageSystemImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: valery
 * Date: 01.10.13
 * Time: 0:05
 * To change this template use File | Settings | File Templates.
 */

public class AccountServiceTest {
    private MessageSystem messageSystem;

    @Before
    public void before(){
        messageSystem  = new MessageSystemImpl();
    }

    @Test
    public void checkConstructor(){
        AccountService accountService = new AccountServiceImpl(messageSystem);
        assertEquals(accountService.getMessageSystem(), messageSystem);
    }

    @Test
    public void checkGetUserId(){
        AccountService accountService = new AccountServiceImpl(messageSystem);
        String nick = "Valera", pass = "valera123";
        int id = accountService.getUserId(nick, pass);
        assertEquals(id, accountService.getUserId(nick,pass));
    }
}
