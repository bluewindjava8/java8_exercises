
package ex_03_17;

import java.security.InvalidParameterException;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Ex_03_17 {


    public static void main(String[] args) {
        test1();
    }
    
    public static void test1(){
        
        
        
        doInParalleAsync(()->System.out.println("Java8"), ()->System.out.println(8/0),
                t -> System.out.println("Throwable: " + t));
    }
    
    
    public static void doInParalleAsync(Runnable first, Runnable second, Consumer<Throwable> handler){
        Thread t1 = new Thread(){
            @Override
            public void run(){
                try{
                    first.run();
                }catch(Throwable t){
                    handler.accept(t);
                }
            }
        };
        
        Thread t2 = new Thread(){
            @Override
            public void run(){
                try{
                    second.run();
                }catch(Throwable t){
                    handler.accept(t);
                }
            }
        };
        
        t1.start();
        t2.start();
    }
}
