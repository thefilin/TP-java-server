import dbService.UserDataSet;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: valery
 * Date: 01.10.13
 * Time: 1:18
 * To change this template use File | Settings | File Templates.
 */
public class UDSTest {
    private int id, rating, winQuantity, loseQuantity;
    private String nick, color ;

    @Before
    public void before(){
        id = 100500; rating = 1500;
        winQuantity = loseQuantity = 0;
        nick = "Valera";
        color = "Black";
    }

    @Test
    public void testFreeConstructor(){
        UserDataSet uds = new UserDataSet();
        uds.setColor(color);
        assertEquals(uds.getNick(), "");
        assertEquals(uds.getColor(), color);
        assertEquals(uds.getId(), 0);
        assertEquals(uds.getLoseQuantity(), 0);
        assertEquals(uds.getWinQuantity(), 0);
        assertEquals(uds.getRating(), 0);

        uds.win(10);
        assertEquals(uds.getWinQuantity(), 1);
        assertEquals(uds.getRating(), 10);

        uds.lose(10);
        assertEquals(uds.getLoseQuantity(), 1);
        assertEquals(uds.getRating(), 0);

        assertEquals(uds.getPostStatus(), 0);
        uds.setPostStatus(1);
        assertEquals(uds.getPostStatus(), 1);
    }

    @Test
    public void testFullConstructor(){
        UserDataSet uds = new UserDataSet(id, nick, rating, winQuantity, loseQuantity);
        uds.setColor(color);
        assertEquals(uds.getNick(), nick);
        assertEquals(uds.getColor(), color);
        assertEquals(uds.getId(), id);
        assertEquals(uds.getLoseQuantity(), loseQuantity);
        assertEquals(uds.getWinQuantity(), winQuantity);
        assertEquals(uds.getRating(), rating);

        uds.win(10);
        assertEquals(uds.getWinQuantity(), winQuantity + 1);
        assertEquals(uds.getRating(), rating + 10);

        uds.lose(10);
        assertEquals(uds.getLoseQuantity(), loseQuantity + 1);
        assertEquals(uds.getRating(), rating);

        assertEquals(uds.getPostStatus(), 0);
        uds.setPostStatus(1);
        assertEquals(uds.getPostStatus(), 1);
    }

    @Test
    public void testLike(){
        UserDataSet udsTemp = new UserDataSet(id, nick, rating, winQuantity, loseQuantity);
        UserDataSet uds = new UserDataSet(); uds.makeLike(udsTemp);
        uds.setColor(color);
        assertEquals(uds.getNick(), nick);
        assertEquals(uds.getColor(), color);
        assertEquals(uds.getId(), id);
        assertEquals(uds.getLoseQuantity(), loseQuantity);
        assertEquals(uds.getWinQuantity(), winQuantity);
        assertEquals(uds.getRating(), rating);

        uds.win(10);
        assertEquals(uds.getWinQuantity(), winQuantity+1);
        assertEquals(uds.getRating(), rating+10);

        uds.lose(10);
        assertEquals(uds.getLoseQuantity(), loseQuantity+1);
        assertEquals(uds.getRating(), rating);

        assertEquals(uds.getPostStatus(), 0);
        uds.setPostStatus(1);
        assertEquals(uds.getPostStatus(), 1);
    }
}
