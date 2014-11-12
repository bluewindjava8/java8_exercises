
package ex_03_21;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;

public class Ex_03_21 {

    public static void main(String[] args) {

    }
    
    public static <T, U> Future<U> map(Future<T> srcFuture, Function<T, U> fun){
        return new Future<U> (){

            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                return srcFuture.cancel(mayInterruptIfRunning);
            }

            @Override
            public boolean isCancelled() {
                return srcFuture.isCancelled();
            }

            @Override
            public boolean isDone() {
                return srcFuture.isDone();
            }

            @Override
            public U get() throws InterruptedException, ExecutionException {
                return fun.apply(srcFuture.get());
                
            }

            @Override
            public U get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
                return fun.apply(srcFuture.get(timeout, unit));
            }
            
        };

    }
}



//class Task implements Callable<Person>{
//    
//
//    @Override
//    public Person call() throws Exception {
//        return new Person()
//    }
//    
//}
