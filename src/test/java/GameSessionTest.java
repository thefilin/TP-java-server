import static org.junit.Assert.*;

import java.io.OutputStream;
import java.io.PrintStream;

import org.junit.Test;

import gameMechanic.GameSession;

public class GameSessionTest {

	@Test
	public void test() {
		PrintStream printStream = new PrintStream(new OutputStream(){public void write(int b) {}}); 
		PrintStream printStreamOriginal=System.err;
		GameSession gameSession;
		String howItMustBe;
		
		gameSession = new GameSession(1,2);
		System.setErr(printStream);
		
		gameSession.checkStroke(1, 2, 5, 3, 4);
		gameSession.checkStroke(2, 2, 5, 1, 4);
		gameSession.checkStroke(1, 3, 4, 2, 3);
		gameSession.checkStroke(2, 4, 5, 6, 3);
		gameSession.checkStroke(1, 0, 5, 2, 3);
		gameSession.checkStroke(2, 6, 5, 4, 3);
		gameSession.checkStroke(1, 4, 5, 2, 3);
		gameSession.checkStroke(2, 1, 4, 2, 3);
		gameSession.checkStroke(1, 6, 5, 4, 3);
		gameSession.checkStroke(2, 5, 6, 6, 5);
		gameSession.checkStroke(1, 2, 3, 3, 2);
		gameSession.checkStroke(2, 3, 6, 5, 4);
		gameSession.checkStroke(1, 7, 6, 6, 5);
		gameSession.checkStroke(2, 6, 5, 7, 4);
		gameSession.checkStroke(1, 6, 5, 5, 4);
		gameSession.checkStroke(2, 7, 6, 6, 5);
		gameSession.checkStroke(1, 5, 6, 6, 5);
		gameSession.checkStroke(2, 5, 4, 6, 3);
		gameSession.checkStroke(1, 1, 6, 2, 5);
		gameSession.checkStroke(2, 6, 3, 7, 2);
		gameSession.checkStroke(1, 6, 5, 7, 4);
		gameSession.checkStroke(2, 4, 7, 3, 6);
		gameSession.checkStroke(1, 6, 7, 5, 6);
		gameSession.checkStroke(2, 6, 7, 5, 6);
		gameSession.checkStroke(1, 5, 6, 4, 5);
		gameSession.checkStroke(2, 3, 6, 2, 5);
		gameSession.checkStroke(1, 4, 5, 3, 4);
		gameSession.checkStroke(2, 2, 7, 3, 6);
		gameSession.checkStroke(1, 3, 4, 2, 3);
		gameSession.checkStroke(2, 6, 5, 4, 3);
		gameSession.checkStroke(2, 4, 3, 6, 1);
		gameSession.checkStroke(1, 0, 7, 2, 5);
		gameSession.checkStroke(2, 2, 5, 4, 3);
		gameSession.checkStroke(2, 4, 3, 6, 1);
		gameSession.checkStroke(1, 3, 6, 2, 5);
		gameSession.checkStroke(2, 6, 1, 4, 3);
		gameSession.checkStroke(1, 2, 7, 1, 6);
		gameSession.checkStroke(2, 7, 2, 5, 0);
		gameSession.checkStroke(2, 5, 0, 1, 4);
		gameSession.checkStroke(1, 7, 4, 5, 2);
		gameSession.checkStroke(1, 5, 2, 3, 0);
		gameSession.checkStroke(1, 3, 0, 1, 2);
		gameSession.checkStroke(1, 1, 2, 6, 7);//43
		
		howItMustBe=
				"{'status':'snapshot','next':'b','color':'w'," +
				"'field':" +
				"[['nothing', 'nothing', 'nothing', 'nothing', 'white', 'nothing', 'white', 'nothing'], " +
				"['nothing', 'nothing', 'nothing', 'nothing', 'nothing', 'nothing', 'nothing', 'nothing'], " +
				"['nothing', 'nothing', 'nothing', 'nothing', 'nothing', 'nothing', 'nothing', 'nothing'], " +
				"['nothing', 'nothing', 'nothing', 'nothing', 'nothing', 'nothing', 'nothing', 'nothing'], " +
				"['black', 'nothing', 'nothing', 'nothing', 'nothing', 'nothing', 'nothing', 'nothing'], " +
				"['nothing', 'nothing', 'nothing', 'nothing', 'nothing', 'nothing', 'nothing', 'black'], " +
				"['nothing', 'nothing', 'nothing', 'nothing', 'nothing', 'nothing', 'black', 'nothing'], " +
				"['nothing', 'nothing', 'nothing', 'nothing', 'nothing', 'nothing', 'nothing', 'black']]," +
				"'king':" +
				"[['false', 'false', 'false', 'false', 'false', 'false', 'true', 'false'], " +
				"['false', 'false', 'false', 'false', 'false', 'false', 'false', 'false'], " +
				"['false', 'false', 'false', 'false', 'false', 'false', 'false', 'false'], " +
				"['false', 'false', 'false', 'false', 'false', 'false', 'false', 'false'], " +
				"['false', 'false', 'false', 'false', 'false', 'false', 'false', 'false'], " +
				"['false', 'false', 'false', 'false', 'false', 'false', 'false', 'false'], " +
				"['false', 'false', 'false', 'false', 'false', 'false', 'false', 'false'], " +
				"['false', 'false', 'false', 'false', 'false', 'false', 'false', 'false']]}";

		gameSession.saveLog(1);
		System.setErr(printStreamOriginal);
		assertEquals(gameSession.getSnapshot(1).toStringTest(),howItMustBe);
		System.out.println("test1 passed");
		
		
		
		gameSession=new GameSession(1,2);
		System.setErr(printStream);
		
		gameSession.checkStroke(1,2,5,3,4);
		gameSession.checkStroke(2,2,5,3,4);
		gameSession.checkStroke(1,3,4,5,2);
		gameSession.checkStroke(2,1,6,3,4);
		gameSession.checkStroke(1,4,5,5,4);
		gameSession.checkStroke(2,3,4,4,3);
		gameSession.checkStroke(1,5,4,6,3);
		gameSession.checkStroke(2,0,5,2,3);
		gameSession.checkStroke(1,6,5,4,3);
		gameSession.checkStroke(1,4,3,2,5);
		
		howItMustBe=
				"{'status':'snapshot','next':'b','color':'w'," +
				"'field':" +
				"[['white', 'nothing', 'white', 'nothing', 'white', 'nothing', 'white', 'nothing'], " +
				"['nothing', 'white', 'nothing', 'white', 'nothing', 'white', 'nothing', 'white'], " +
				"['white', 'nothing', 'white', 'nothing', 'nothing', 'nothing', 'nothing', 'nothing'], " +
				"['nothing', 'nothing', 'nothing', 'nothing', 'nothing', 'nothing', 'nothing', 'nothing'], " +
				"['nothing', 'nothing', 'nothing', 'nothing', 'nothing', 'nothing', 'nothing', 'nothing'], " +
				"['nothing', 'black', 'nothing', 'black', 'nothing', 'nothing', 'nothing', 'nothing'], " +
				"['black', 'nothing', 'black', 'nothing', 'black', 'nothing', 'nothing', 'nothing'], " +
				"['nothing', 'black', 'nothing', 'black', 'nothing', 'black', 'nothing', 'black']]," +
				"'king':" +
				"[['false', 'false', 'false', 'false', 'false', 'false', 'false', 'false'], " +
				"['false', 'false', 'false', 'false', 'false', 'false', 'false', 'false'], " +
				"['false', 'false', 'false', 'false', 'false', 'false', 'false', 'false'], " +
				"['false', 'false', 'false', 'false', 'false', 'false', 'false', 'false'], " +
				"['false', 'false', 'false', 'false', 'false', 'false', 'false', 'false'], " +
				"['false', 'false', 'false', 'false', 'false', 'false', 'false', 'false'], " +
				"['false', 'false', 'false', 'false', 'false', 'false', 'false', 'false'], " +
				"['false', 'false', 'false', 'false', 'false', 'false', 'false', 'false']]}";

		System.setErr(printStreamOriginal);
		assertEquals(gameSession.getSnapshot(1).toStringTest(),howItMustBe);
		System.out.println("test2 passed");
		
		
		
		gameSession = new GameSession(1,2);
		System.setErr(printStream);
		
		gameSession.checkStroke(1,2,5,3,4);
		gameSession.checkStroke(2,2,5,3,4);
		gameSession.checkStroke(1,3,4,5,2);
		gameSession.checkStroke(2,1,6,3,4);
		gameSession.checkStroke(1,4,5,3,4);
		gameSession.checkStroke(2,3,4,5,2);
		gameSession.checkStroke(1,1,6,3,4);
		gameSession.checkStroke(2,4,5,3,4);
		gameSession.checkStroke(1,3,4,5,2);
		gameSession.checkStroke(2,3,6,1,4);
		gameSession.checkStroke(1,6,5,7,4);
		gameSession.checkStroke(2,2,7,1,6);
		gameSession.checkStroke(1,7,4,5,2);
		gameSession.checkStroke(2,1,6,3,4);
		gameSession.checkStroke(1,5,6,4,5);
		gameSession.checkStroke(2,6,5,5,4);
		gameSession.checkStroke(1,4,5,3,4);
		gameSession.checkStroke(2,5,4,3,2);
		gameSession.checkStroke(1,3,6,5,4);
		gameSession.checkStroke(1,5,4,3,2);
		gameSession.checkStroke(2,5,6,3,4);
		gameSession.checkStroke(1,7,6,6,5);
		gameSession.checkStroke(2,0,7,1,6);
		gameSession.checkStroke(1,6,5,7,4);
		gameSession.checkStroke(2,1,6,2,5);
		gameSession.checkStroke(1,6,7,5,6);
		gameSession.checkStroke(2,0,5,1,4);
		gameSession.checkStroke(1,5,6,4,5);
		gameSession.checkStroke(2,3,4,4,3);
		gameSession.checkStroke(1,4,5,2,3);
		gameSession.checkStroke(2,1,4,2,3);
		gameSession.checkStroke(1,0,7,1,6);
		gameSession.checkStroke(2,2,5,3,4);
		gameSession.checkStroke(1,1,6,2,5);
		gameSession.checkStroke(2,2,3,3,2);
		gameSession.checkStroke(1,2,5,1,4);
		gameSession.checkStroke(2,3,4,2,3);
		gameSession.checkStroke(1,2,3,1,2);
		gameSession.checkStroke(2,7,6,5,4);
		gameSession.checkStroke(1,1,4,3,2);
		gameSession.checkStroke(2,4,7,5,6);
		gameSession.checkStroke(1,3,2,4,1);
		gameSession.checkStroke(2,5,6,6,5);
		gameSession.checkStroke(1,4,1,3,0);
		gameSession.checkStroke(2,6,7,7,6);
		gameSession.checkStroke(1,3,0,0,3);
		
		howItMustBe=
				"{'status':'snapshot','next':'b','color':'w'," +
				"'field':" +
				"[['nothing', 'nothing', 'white', 'nothing', 'white', 'nothing', 'nothing', 'nothing'], " +
				"['nothing', 'nothing', 'nothing', 'nothing', 'nothing', 'nothing', 'nothing', 'nothing'], " +
				"['white', 'nothing', 'nothing', 'nothing', 'black', 'nothing', 'nothing', 'nothing'], " +
				"['nothing', 'nothing', 'nothing', 'nothing', 'nothing', 'black', 'nothing', 'white'], " +
				"['white', 'nothing', 'nothing', 'nothing', 'nothing', 'nothing', 'nothing', 'nothing'], " +
				"['nothing', 'nothing', 'nothing', 'nothing', 'nothing', 'nothing', 'nothing', 'nothing'], " +
				"['black', 'nothing', 'nothing', 'nothing', 'nothing', 'nothing', 'nothing', 'nothing'], " +
				"['nothing', 'nothing', 'nothing', 'nothing', 'nothing', 'nothing', 'nothing', 'nothing']]," +
				"'king':" +
				"[['false', 'false', 'false', 'false', 'false', 'false', 'false', 'false'], " +
				"['false', 'false', 'false', 'false', 'false', 'false', 'false', 'false'], " +
				"['false', 'false', 'false', 'false', 'false', 'false', 'false', 'false'], " +
				"['false', 'false', 'false', 'false', 'false', 'false', 'false', 'false'], " +
				"['true', 'false', 'false', 'false', 'false', 'false', 'false', 'false'], " +
				"['false', 'false', 'false', 'false', 'false', 'false', 'false', 'false'], " +
				"['false', 'false', 'false', 'false', 'false', 'false', 'false', 'false'], " +
				"['false', 'false', 'false', 'false', 'false', 'false', 'false', 'false']]}";

		System.setErr(printStreamOriginal);
		assertEquals(gameSession.getSnapshot(1).toStringTest(),howItMustBe);
		assertEquals(gameSession.getWinnerId(), 0);
		System.out.println("test3 passed");
		
		
		
		gameSession = new GameSession(1,2);
		System.setErr(printStream);
		gameSession.checkStroke(1,2,5,3,4);
		gameSession.checkStroke(2,6,5,7,4);
		gameSession.checkStroke(1,3,4,2,3);
		gameSession.checkStroke(2,4,5,6,3);
		gameSession.checkStroke(1,0,5,2,3);
		gameSession.checkStroke(2,5,6,6,5);
		gameSession.checkStroke(1,1,6,0,5);
		gameSession.checkStroke(2,6,5,4,3);
		gameSession.checkStroke(1,4,5,2,3);
		gameSession.checkStroke(2,0,5,1,4);
		gameSession.checkStroke(1,3,6,2,5);
		gameSession.checkStroke(2,1,4,0,3);
		gameSession.checkStroke(1,2,7,3,6);
		gameSession.checkStroke(2,6,7,5,6);
		gameSession.checkStroke(1,2,3,1,2);
		gameSession.checkStroke(2,7,6,5,4);
		gameSession.checkStroke(1,2,5,1,4);
		gameSession.checkStroke(2,7,4,5,2);
		gameSession.checkStroke(1,3,6,1,4);
		gameSession.checkStroke(1,1,4,3,2);
		gameSession.checkStroke(1,3,2,1,0);
		gameSession.checkStroke(2,6,7,4,5);
		gameSession.checkStroke(1,3,2,5,4);
		
		howItMustBe=
				"{'status':'snapshot','next':'b','color':'w'," +
				"'field':" +
				"[['white', 'nothing', 'nothing', 'nothing', 'white', 'nothing', 'white', 'nothing'], " +
				"['nothing', 'nothing', 'nothing', 'nothing', 'nothing', 'white', 'nothing', 'white'], " +
				"['white', 'nothing', 'nothing', 'nothing', 'nothing', 'nothing', 'white', 'nothing'], " +
				"['nothing', 'nothing', 'nothing', 'nothing', 'nothing', 'nothing', 'nothing', 'black'], " +
				"['nothing', 'nothing', 'nothing', 'nothing', 'nothing', 'nothing', 'nothing', 'nothing'], " +
				"['nothing', 'nothing', 'nothing', 'nothing', 'nothing', 'black', 'nothing', 'nothing'], " +
				"['nothing', 'nothing', 'nothing', 'nothing', 'black', 'nothing', 'black', 'nothing'], " +
				"['nothing', 'white', 'nothing', 'black', 'nothing', 'black', 'nothing', 'black']]," +
				"'king':" +
				"[['false', 'false', 'false', 'false', 'false', 'false', 'false', 'false'], " +
				"['false', 'false', 'false', 'false', 'false', 'false', 'false', 'false'], " +
				"['false', 'false', 'false', 'false', 'false', 'false', 'false', 'false'], " +
				"['false', 'false', 'false', 'false', 'false', 'false', 'false', 'false'], " +
				"['false', 'false', 'false', 'false', 'false', 'false', 'false', 'false'], " +
				"['false', 'false', 'false', 'false', 'false', 'false', 'false', 'false'], " +
				"['false', 'false', 'false', 'false', 'false', 'false', 'false', 'false'], " +
				"['false', 'true', 'false', 'false', 'false', 'false', 'false', 'false']]}";

		System.setErr(printStreamOriginal);
		assertEquals(gameSession.getSnapshot(1).toStringTest(),howItMustBe);
		System.out.println("test4 passed");

		
		
		gameSession = new GameSession(1,2);
		System.setErr(printStream);
		
		gameSession.checkStroke(1,2,5,3,4);
		gameSession.checkStroke(2,2,5,3,4);
		gameSession.checkStroke(1,3,4,5,2);
		gameSession.checkStroke(2,1,6,3,4);
		gameSession.checkStroke(1,4,5,5,4);
		gameSession.checkStroke(2,3,4,4,3);
		gameSession.checkStroke(1,5,4,6,3);
		gameSession.checkStroke(2,0,5,2,3);
		gameSession.checkStroke(1,6,5,4,3);
		gameSession.checkStroke(1,4,3,2,5);
		gameSession.checkStroke(2,6,5,5,4);
		gameSession.checkStroke(1,3,6,4,5);
		gameSession.checkStroke(2,3,6,2,5);
		gameSession.checkStroke(1,7,6,6,5);
		gameSession.checkStroke(2,4,7,3,6);
		gameSession.checkStroke(1,6,5,7,4);
		gameSession.checkStroke(2,2,5,3,4);
		gameSession.checkStroke(1,4,5,3,4);
		gameSession.checkStroke(2,5,4,3,2);
		gameSession.checkStroke(1,5,6,3,4);
		gameSession.checkStroke(1,3,4,5,2);
		gameSession.checkStroke(1,5,2,3,0);
		gameSession.checkStroke(1,3,0,0,3);
		gameSession.checkStroke(2,7,6,6,5);		
		gameSession.checkStroke(1,0,3,2,1);		
		gameSession.checkStroke(1,0,3,3,0);
		gameSession.checkStroke(1,2,1,4,3);
		
		howItMustBe=
				"{'status':'snapshot','next':'b','color':'w'," +
				"'field':" +
				"[['white', 'nothing', 'white', 'nothing', 'white', 'nothing', 'white', 'nothing'], " +
				"['nothing', 'white', 'nothing', 'nothing', 'nothing', 'nothing', 'nothing', 'nothing'], " +
				"['white', 'nothing', 'white', 'nothing', 'nothing', 'nothing', 'nothing', 'nothing'], " +
				"['nothing', 'nothing', 'nothing', 'nothing', 'nothing', 'nothing', 'nothing', 'white'], " +
				"['nothing', 'nothing', 'nothing', 'nothing', 'white', 'nothing', 'nothing', 'nothing'], " +
				"['nothing', 'nothing', 'nothing', 'nothing', 'nothing', 'nothing', 'nothing', 'nothing'], " +
				"['nothing', 'nothing', 'nothing', 'nothing', 'nothing', 'nothing', 'nothing', 'nothing'], " +
				"['nothing', 'black', 'nothing', 'nothing', 'nothing', 'black', 'nothing', 'black']]," +
				"'king':" +
				"[['false', 'false', 'false', 'false', 'false', 'false', 'false', 'false'], " +
				"['false', 'false', 'false', 'false', 'false', 'false', 'false', 'false'], " +
				"['false', 'false', 'false', 'false', 'false', 'false', 'false', 'false'], " +
				"['false', 'false', 'false', 'false', 'false', 'false', 'false', 'false'], " +
				"['false', 'false', 'false', 'false', 'true', 'false', 'false', 'false'], " +
				"['false', 'false', 'false', 'false', 'false', 'false', 'false', 'false'], " +
				"['false', 'false', 'false', 'false', 'false', 'false', 'false', 'false'], " +
				"['false', 'false', 'false', 'false', 'false', 'false', 'false', 'false']]}";

		System.setErr(printStreamOriginal);
		assertEquals(gameSession.getSnapshot(1).toStringTest(),howItMustBe);
		System.out.println("test5 passed");
	}

    @Test
    public void checkSnapshot(){
        String snapshot = "{\"status\":\"snapshot\",\"next\":\"w\",\"color\":\"w\",\"field\":[[\"white\", \"nothing\", \"white\", \"nothing\", \"white\", \"nothing\", \"white\", \"nothing\"], [\"nothing\", \"white\", \"nothing\", \"white\", \"nothing\", \"white\", \"nothing\", \"white\"], [\"white\", \"nothing\", \"white\", \"nothing\", \"white\", \"nothing\", \"white\", \"nothing\"], [\"nothing\", \"nothing\", \"nothing\", \"nothing\", \"nothing\", \"nothing\", \"nothing\", \"nothing\"], [\"nothing\", \"nothing\", \"nothing\", \"nothing\", \"nothing\", \"nothing\", \"nothing\", \"nothing\"], [\"nothing\", \"black\", \"nothing\", \"black\", \"nothing\", \"black\", \"nothing\", \"black\"], [\"black\", \"nothing\", \"black\", \"nothing\", \"black\", \"nothing\", \"black\", \"nothing\"], [\"nothing\", \"black\", \"nothing\", \"black\", \"nothing\", \"black\", \"nothing\", \"black\"]],\"king\":[[\"false\", \"false\", \"false\", \"false\", \"false\", \"false\", \"false\", \"false\"], [\"false\", \"false\", \"false\", \"false\", \"false\", \"false\", \"false\", \"false\"], [\"false\", \"false\", \"false\", \"false\", \"false\", \"false\", \"false\", \"false\"], [\"false\", \"false\", \"false\", \"false\", \"false\", \"false\", \"false\", \"false\"], [\"false\", \"false\", \"false\", \"false\", \"false\", \"false\", \"false\", \"false\"], [\"false\", \"false\", \"false\", \"false\", \"false\", \"false\", \"false\", \"false\"], [\"false\", \"false\", \"false\", \"false\", \"false\", \"false\", \"false\", \"false\"], [\"false\", \"false\", \"false\", \"false\", \"false\", \"false\", \"false\", \"false\"]]}";
        GameSession gameSession = new GameSession(1,2);
        assertEquals(gameSession.getSnapshot(1).toString(), snapshot);
    }
}
