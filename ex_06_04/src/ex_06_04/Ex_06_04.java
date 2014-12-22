
package ex_06_04;

import java.util.Arrays;
import java.util.concurrent.atomic.LongAccumulator;


public class Ex_06_04 {

    public static void main(String[] args) {
        
    }
    
    public static long getMax(long[] values){
        LongAccumulator adder = new LongAccumulator(Math::max, Long.MIN_VALUE);        
        Arrays.stream(values).parallel().forEach(value -> adder.accumulate(value));
        return adder.longValue();
    }
    
}
