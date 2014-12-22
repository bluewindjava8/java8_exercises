
package test_06_04;

import static ex_06_04.Ex_06_04.*;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class Test_06_04 {

    @Test
    public void testFewNumbers(){
        long[] values = new long[]{1, 2, -10, 100, 5000, Long.MIN_VALUE, 5000000, -60};
        long result = getMax(values);
        assertEquals(result, 5000000);
    }
    
    @Test
    public void testManyNumbers(){
        //long[] values = Stream.iterate(0, x -> x + 1).limit(10000).mapToLong(value -> value.longValue()).toArray();
        long[] values = LongStream.range(0, 100000).toArray();
        long result = getMax(values);
        assertEquals(result, 100000 - 1);
        
        
    }
}
