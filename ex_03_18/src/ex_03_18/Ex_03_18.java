
package ex_03_18;

import java.io.File;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;


public class Ex_03_18 {
    
    public static void main(String[] args) {
        FunctionEx<String, Boolean> f = fileName -> new File(fileName).createNewFile();
        ///Function<String, Boolean> f1 = fileName -> new File(fileName).createNewFile();
        Function<String, Boolean> f1 = unchecked(f);
        f1.apply("testtest.txt");
    }
    
    public static <T, U> Function<T, U> unchecked(FunctionEx<T, U> f){
        return t -> {
            try{
                U u = f.apply(t);
                return u;
            }catch(Exception e){
                throw new RuntimeException(e);
            }
        };
    }
    
}

interface FunctionEx<T, U>{
    U apply(T t)throws Exception;
}