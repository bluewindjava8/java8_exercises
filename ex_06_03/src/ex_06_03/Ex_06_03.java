
package ex_06_03;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;


public class Ex_06_03 {


    public static void main(String[] args) throws InterruptedException {
        Instant start1 = Instant.now();
        testAtomicLong();
        Instant end1 = Instant.now();
        System.out.println(Duration.between(start1, end1).toMillis());
        
        Instant start2 = Instant.now();
        testLongAdder();
        Instant end2 = Instant.now();
        System.out.println(Duration.between(start2, end2).toMillis());
    }
    
    public static void testAtomicLong() throws InterruptedException{
        CountDownLatch latch = new CountDownLatch(1000);
        AtomicLong accumulator = new AtomicLong(0);
        
        for(int i = 0; i < 1000; i++){
            new Thread(() -> {
                for(int j = 0; j < 100000; j++){
                    accumulator.incrementAndGet();
                }
                
                latch.countDown();
            }).start();
        }
        
        latch.await();
        
    }
    
    public static void testLongAdder() throws InterruptedException{
        CountDownLatch latch = new CountDownLatch(1000);
        LongAdder accumulator = new LongAdder();
        
        for(int i = 0; i < 1000; i++){
            new Thread(() -> {
                for(int j = 0; j < 100000; j++){
                    accumulator.increment();
                }
                
                latch.countDown();
            }).start();
        }
        
        latch.await();
    }
    
}
