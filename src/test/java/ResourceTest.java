import org.junit.Test;
import resource.Rating;
import resource.TimeSettings;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: valery
 * Date: 01.10.13
 * Time: 1:31
 * To change this template use File | Settings | File Templates.
 */
public class ResourceTest {
    private int defaultValue;

    @Test
    public void testRating(){
        assertEquals(Rating.getAvgDiff(), defaultValue);
        assertEquals(Rating.getDiff(1020, 10504), defaultValue);
    }

    @Test
    public void testTimeSettings(){
        assertEquals(TimeSettings.getExitTime(), defaultValue);
    }
}
