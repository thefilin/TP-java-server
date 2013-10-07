import main.Main;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Valery Ozhiganov
 * Date: 06.10.13
 * Time: 0:05
 * To change this template use File | Settings | File Templates.
 */
public class MainClassTest {
    @Test
    public void test() throws Exception {
        Main main = new Main();
        Main.main(null);
    }
}
