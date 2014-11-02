
package ex_1_8;

import java.util.ArrayList;
import java.util.List;

public class Ex_1_8 {

    public static void main(String[] args) {
        testExFor();
        testOrdinaryFor();
    }
    
    public static void testExFor(){
        String[] names = {"Peter", "Paul", "Mary"};
        List<Runnable> runners = new ArrayList<>();
        
        //拡張for文の中のnameは実質的finalと見なされるため、正当です。
        for(String name : names){
            runners.add(()->System.out.println(name));
        }
        
        for(Runnable runner : runners){
            runner.run();
        }
        
    }
    
    public static void testOrdinaryFor(){
        String[] names = {"Peter", "Paul", "Mary"};
        List<Runnable> runners = new ArrayList<>();
            //iは実質的finalと見なされないため、不正当です。
        //String name;
        for(int i = 0; i < names.length; i++){
            String name = names[i];
            //name = names[i];
            runners.add(()->System.out.println(name));
        }
        
        for(Runnable runner : runners){
            runner.run();
        }
    }
}
