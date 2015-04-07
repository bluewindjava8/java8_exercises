
package test_09_09;

import ex_09_09.LabeledPoint;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class Test_09_09 {
    
    @Test
    public void test(){
        LabeledPoint p1 = new LabeledPoint("test", 1, 2);
        LabeledPoint p2 = new LabeledPoint("test", 1, 2);
        LabeledPoint p3 = new LabeledPoint("test3", 1, 20);
        LabeledPoint p4 = new LabeledPoint("test3", 1, 2);
        
        assertEquals(p1, p2);
        assertEquals(p1.hashCode(), p2.hashCode());
        assertFalse(p1.equals(p3));
        assertFalse(p3.equals(p4));
    }
}
