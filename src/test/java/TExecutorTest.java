import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import dbService.TExecutor;
import dbService.TResultHandler;

import static org.mockito.Mockito.*;



public class TExecutorTest {

	@Test
	public void execQueryTest() {
		Connection mockConn = mock(Connection.class);
		TResultHandler<Object> mockHandler = mock(TResultHandler.class);
		try {
			when(mockConn.createStatement()).thenReturn(null);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		TExecutor.execQuery(mockConn, "Fake query", mockHandler);
		
		try {
			verify(mockConn).createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
