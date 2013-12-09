import org.junit.Test;
import utils.SysInfo;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: nikita
 * Date: 31.10.13
 * Time: 2:27
 * To change this template use File | Settings | File Templates.
 */
public class GetStatTest {
    @Test
    public void GetStatTest_mu()
    {
        Map<String,String> data= new HashMap<String,String>();
        String mu= SysInfo.getStat("MemoryUsage");
        data.put("MemoryUsage", mu);
        assertEquals(mu,data.get("MemoryUsage"));
    }

    @Test
    public void GetStatTest_tm()
    {
        Map<String,String> data= new HashMap<String,String>();
        String tm = SysInfo.getStat("TotalMemory");
        data.put("TotalMemory", tm);
        assertEquals(tm,data.get("TotalMemory"));
    }

    @Test
    public void GetStatTest_time()
    {
    Map<String,String> data= new HashMap<String,String>();
    String time=SysInfo.getStat("Time");
    data.put("Time", time);
    assertEquals(time,data.get("Time"));
    }
    @Test
    public void GetStatTest_ccu()
    {
        Map<String,String> data= new HashMap<String,String>();
        String ccu = SysInfo.getStat("CCU");
        data.put("CCU", ccu);
        assertEquals(ccu,data.get("CCU"));
    }
  }
