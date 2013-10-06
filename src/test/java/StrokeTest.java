import gameClasses.Stroke;
import junit.framework.Assert;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Valery Ozhiganov
 * Date: 06.10.13
 * Time: 15:13
 * To change this template use File | Settings | File Templates.
 */
public class StrokeTest {
    @Test
    public void checkConstructor1(){
        Stroke stroke = new Stroke(0,0,0,0,"status");
        String ans = "{\"color\":\"\",\"to_x\":0,\"to_y\":0,\"from_x\":0,\"from_y\":0,\"status\":\"status\",\"next\":\"0\"}";
        Assert.assertEquals(stroke.toString(), ans);
    }

    @Test
    public void checkConstructor2(){
        Stroke stroke = new Stroke(1,2,3,4,"status", "color");
        Assert.assertTrue(stroke.getFrom_X() == 3);
        Assert.assertTrue(stroke.getFrom_Y()==4);
        Assert.assertTrue(stroke.getTo_X()==1);
        Assert.assertTrue(stroke.getTo_Y()==2);
        Assert.assertFalse(stroke.isEmpty());
    }

    @Test
    public void checkConstructor3(){
        Stroke stroke = new Stroke("status");
        Assert.assertTrue(stroke.isEmpty());
        Assert.assertEquals(stroke.getStatus(),"status");
    }

    @Test
    public void checkConstructor4(){
        Stroke stroke = new Stroke(0,0,0,0,"status","w",'0');
        Assert.assertEquals(stroke.getColor(),"w");
        Assert.assertEquals(stroke.getNext(),'0');
        String ans = "{\"color\":\"b\",\"to_x\":7,\"to_y\":7,\"from_x\":7,\"from_y\":7,\"status\":\"status\",\"next\":\"0\"}";
        Assert.assertEquals(stroke.getInverse().toString(), ans);

        stroke.setColor("b");
        ans = "{\"color\":\"w\",\"to_x\":7,\"to_y\":7,\"from_x\":7,\"from_y\":7,\"status\":\"status\",\"next\":\"0\"}";
        Assert.assertEquals(stroke.getInverse().toString(), ans);
    }

    @Test
    public void checkConstructor5(){
        Stroke stroke1 = new Stroke();
        Stroke stroke2 = new Stroke(stroke1);

        stroke1.setFrom_X(1);
        stroke1.setFrom_Y(2);
        stroke1.setTo_X(3);
        stroke1.setTo_Y(4);
        stroke2.fullSet(3, 4, 1, 2);

        Assert.assertEquals(stroke1.toString(), stroke2.toString());

        stroke1.clear();
        stroke1.setNext('n');
        stroke1.setStatus("status");
        String ans = "{\"color\":\"\",\"to_x\":-1,\"to_y\":-1,\"from_x\":-1,\"from_y\":-1,\"status\":\"status\",\"next\":\"n\"}";
        Assert.assertEquals(stroke1.toString(), ans);
    }
}
