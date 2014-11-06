
package ex_03_18;

import java.util.List;
import java.util.function.BiFunction;


public class Ex_03_18 {
    //問題文の意味は理解できません。
    public static void main(String[] args) {

    }
    
    public static void test(List<? super String> list){
        //String s = list.get(0);
        list.add("ss");
        
        Integer i = bif.apply(1, new String(""));
        Integer ii = bif.apply(1, "sss");
    }
    
    static BiFunction<? super Integer, ? super String, Integer> bif = (length,  s) -> length + s.length();
    
    BiFunction<Integer, ? super String, Integer> bif2 = new BiFunction<Integer, String, Integer>(){
        public Integer apply(Integer i, String s){
            return 1;
        }
    };
    
    BiFunction<Integer, String, Integer> bif3 = new BiFunction<Integer, String, Integer>(){
        public Integer apply(Integer i, String s){
            return 1;
        }
    };
}
