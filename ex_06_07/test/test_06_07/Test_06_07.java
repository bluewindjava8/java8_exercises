
package test_06_07;

import static ex_06_07.Ex_06_07.*;
import java.util.concurrent.ConcurrentHashMap;
import static junit.framework.Assert.assertEquals;
import org.junit.Test;

public class Test_06_07 {
    @Test
    public void test(){
        ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>();
        for(long i = 0; i < 100000; i++){
            map.put(Long.toString(i), i);
        }
        
        String keyOfMaxValue = findKeyOfMaxValue(map);
        assertEquals("99999", keyOfMaxValue);
    }
}
