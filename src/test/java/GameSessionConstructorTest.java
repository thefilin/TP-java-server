import static org.junit.Assert.*;
import gameClasses.Snapshot;

import org.junit.Test;

import gameMechanic.GameSession;

public class GameSessionConstructorTest {

	@Test
	public void test() {
		int fieldSize= 8, playerSize = 3;
		GameSession gameSession = new GameSession(1,2,fieldSize,playerSize);
		Snapshot snapshot = gameSession.getSnapshot(1);
		String howItMustBe ="{'status':'snapshot','next':'w','color':'w','field':[['white', 'nothing', 'white', 'nothing', 'white', 'nothing', 'white', 'nothing'], ['nothing', 'white', 'nothing', 'white', 'nothing', 'white', 'nothing', 'white'], ['white', 'nothing', 'white', 'nothing', 'white', 'nothing', 'white', 'nothing'], ['nothing', 'nothing', 'nothing', 'nothing', 'nothing', 'nothing', 'nothing', 'nothing'], ['nothing', 'nothing', 'nothing', 'nothing', 'nothing', 'nothing', 'nothing', 'nothing'], ['nothing', 'black', 'nothing', 'black', 'nothing', 'black', 'nothing', 'black'], ['black', 'nothing', 'black', 'nothing', 'black', 'nothing', 'black', 'nothing'], ['nothing', 'black', 'nothing', 'black', 'nothing', 'black', 'nothing', 'black']],'king':[['false', 'false', 'false', 'false', 'false', 'false', 'false', 'false'], ['false', 'false', 'false', 'false', 'false', 'false', 'false', 'false'], ['false', 'false', 'false', 'false', 'false', 'false', 'false', 'false'], ['false', 'false', 'false', 'false', 'false', 'false', 'false', 'false'], ['false', 'false', 'false', 'false', 'false', 'false', 'false', 'false'], ['false', 'false', 'false', 'false', 'false', 'false', 'false', 'false'], ['false', 'false', 'false', 'false', 'false', 'false', 'false', 'false'], ['false', 'false', 'false', 'false', 'false', 'false', 'false', 'false']]}";
		assertEquals(snapshot.toStringTest(),howItMustBe);
	}
}
