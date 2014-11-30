
package test_05_07;

import ex_05_07.TimeInterval;
import java.time.LocalTime;
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
    
    @Test 
    public void testOverLapping(){
        
        TimeInterval interval1 = TimeInterval.of(LocalTime.of(1, 0, 0), LocalTime.of(2, 0, 0));
        TimeInterval interval2 = TimeInterval.of(LocalTime.of(0, 0, 0), LocalTime.of(0, 59, 0));
        assertFalse(interval1.isOverlapping(interval2));
        
        interval1 = TimeInterval.of(LocalTime.of(1, 0, 0), LocalTime.of(2, 0, 0));
        interval2 = TimeInterval.of(LocalTime.of(0, 0, 0), LocalTime.of(1, 0, 0));
        assertFalse(interval1.isOverlapping(interval2));
        
        interval1 = TimeInterval.of(LocalTime.of(1, 0, 0), LocalTime.of(2, 0, 0));
        interval2 = TimeInterval.of(LocalTime.of(0, 0, 0), LocalTime.of(1, 0, 1));
        assertTrue(interval1.isOverlapping(interval2));  
        
        
        interval1 = TimeInterval.of(LocalTime.of(1, 0, 0), LocalTime.of(2, 0, 0));
        interval2 = TimeInterval.of(LocalTime.of(1, 0, 0), LocalTime.of(1, 0, 1));
        assertTrue(interval1.isOverlapping(interval2)); 
        
        interval1 = TimeInterval.of(LocalTime.of(1, 0, 0), LocalTime.of(2, 0, 0));
        interval2 = TimeInterval.of(LocalTime.of(1, 0, 0), LocalTime.of(2, 0, 1));
        assertTrue(interval1.isOverlapping(interval2));   
        
        interval1 = TimeInterval.of(LocalTime.of(1, 0, 0), LocalTime.of(2, 0, 0));
        interval2 = TimeInterval.of(LocalTime.of(1, 0, 1), LocalTime.of(1, 29, 0));
        assertTrue(interval1.isOverlapping(interval2)); 
        
        interval1 = TimeInterval.of(LocalTime.of(1, 0, 0), LocalTime.of(2, 0, 0));
        interval2 = TimeInterval.of(LocalTime.of(2, 0, 0), LocalTime.of(2, 0, 1));
        assertFalse(interval1.isOverlapping(interval2));    
        
        interval1 = TimeInterval.of(LocalTime.of(1, 0, 0), LocalTime.of(2, 0, 0));
        interval2 = TimeInterval.of(LocalTime.of(2, 0, 10), LocalTime.of(3, 0, 1));
        assertFalse(interval1.isOverlapping(interval2));   
    }
}
