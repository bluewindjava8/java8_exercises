package test_03_21;

import static ex_03_21.Ex_03_21.map;
import ex_03_21.Person;
import java.util.Comparator;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class Test_03_21 {
    @Test
    public void TestOneField()throws InterruptedException, ExecutionException{
        
        Person person = new Person("tom", 20);
    
        ExecutorService pool = Executors.newCachedThreadPool();
        Future<Person> srcFuture = pool.submit(()->person);
        
        Future<String> destFuture = map(srcFuture, Person::toString);
        String personInfo = destFuture.get();
        
        Assert.assertEquals(person.toString(), personInfo);
    }    
}
