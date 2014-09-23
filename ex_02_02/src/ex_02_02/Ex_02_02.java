package ex_02_02;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Ex_02_02 {

    public static void main(String[] args) {
        String[] words = {"Japan", "China","Chile", "USA", "Brazil","Belgium", "India","Korea"};
        fun(words, 4);

    }
    
    public static void fun(String[] words, int threshold){
        List<String> strs = Stream.of(words).filter(w -> {System.out.println("Filter : "+ w); return w.length() > threshold;})
                .limit(5).collect(Collectors.toList());
        System.out.println(strs);
    }
    
}
