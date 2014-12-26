package ex_03_16;

import java.security.InvalidParameterException;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class Ex_03_16 {

    public static void main(String[] args) {
        test1();
        test2();
    }
    
    public static void test1(){
        Supplier<String> supplier = () -> "Java8";
        BiConsumer<String, Throwable> biConsumer = (s, t) -> {
            if(s != null && t!= null || s == null && t==null ){
                throw new InvalidParameterException("Both parameters are null or both are not null");
            }
            
            if(s != null){
                System.out.println("Result consumed: " + s);
            }else{
                System.out.println("Throwable consumed:" + t);
            }
        };
        
        doInOrderAsync(supplier, biConsumer);
    }
    
        
    public static void test2(){
        Supplier<String> supplier = () -> String.valueOf(8/0);
        
        BiConsumer<String, Throwable> biConsumer = (s, t) -> {
            if(s != null && t!= null || s == null && t==null ){
                throw new InvalidParameterException("Both parameters are null or both are not null");
            }
            
            if(s != null){
                System.out.println("Result consumed: " + s);
            }else{
                System.out.println("Throwable consumed:" + t);
            }
        };
        
        doInOrderAsync(supplier, biConsumer);
    }
    
    //secondは正常結果と例外両方消費できますので、三番目の引数は必要ありません。
    public static <T> void doInOrderAsync(Supplier<T> first, BiConsumer<T, Throwable> second){
        Thread t = new Thread(){
            @Override
            public void run(){
                try{
                     T result = first.get();
                     second.accept(result, null);
                 }catch(Throwable t){
                     second.accept(null, t);
                 } 
            }
        };
        t.start();

    }
}
