package test_03_23;

import ex_03_23.Pair;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.junit.Assert;
import org.junit.Test;

import static ex_03_23.Ex_03_23.map;


public class Test_03_23 {
    @Test
    public void TestOneField()throws InterruptedException, ExecutionException{
        
        Pair<String> srcPair = new Pair<>("Matsuyama Kenichi", "Koyuki");
        Pair<Integer> destPair = map(srcPair, s -> s.length());
        
        Assert.assertEquals((long) destPair.getPart(), (long)17);
        Assert.assertEquals((long) destPair.getCounterPart(), (long)6);        
        
    }     

}
