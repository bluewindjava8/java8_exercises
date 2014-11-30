
package test_05_07;

import ex_05_07.TimeInterval;
import java.time.LocalTime;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Test;

public class Test_05_07 {
    @Test
    public void testIsInside(){
        LocalTime start = LocalTime.of(1, 0, 0);
        LocalTime end = LocalTime.of(2, 0, 0);
        
        TimeInterval interval = TimeInterval.of(start, end);
        assertTrue(interval.isInside(LocalTime.of(1, 20,0)));
        assertFalse(interval.isInside(LocalTime.of(2, 20,0)));
        
    }
    
    @Test
    public void testTwoIntervalsEquals(){
        LocalTime start = LocalTime.of(1, 0, 0);
        LocalTime end = LocalTime.of(2, 0, 0);
        
        TimeInterval interval1 = TimeInterval.of(start, end);
        TimeInterval interval2 = TimeInterval.of(start, end);
        
        assertTrue(interval1.equals(interval2));
        
    }   
}
