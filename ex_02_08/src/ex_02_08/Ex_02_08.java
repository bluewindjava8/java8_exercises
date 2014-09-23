package ex_02_08;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Ex_02_08 {

    public static void main(String[] args) {
        Stream<String> strs1 = Stream.of("1", "2", "3");
        Stream<String> strs2 = Stream.of("4", "5", "6", "7", "8");
        Stream<String> strs3 = zip(strs1, strs2);
        strs3.forEach(System.out::println);
        
        System.out.println();
        
        Stream<String> strs4 = Stream.of("1", "2", "3");
        Stream<String> strs5 = Stream.of("4", "5", "6", "7", "8");        
        Stream<String> strs6 = zip(strs5, strs4);
        strs6.forEach(System.out::println);   
        
         System.out.println();
        
        Stream<String> strs7 = Stream.of();
        Stream<String> strs8 = Stream.of("4", "5", "6", "7", "8");        
        Stream<String> strs9 = zip(strs7, strs8);
        strs9.forEach(System.out::println);         
        
    }
    
    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second){
        List<T> firstList = first.collect(Collectors.toList());
        List<T> secondList = second.collect(Collectors.toList());
        List<T> resultList = new ArrayList<>();
        
        int firstLen = firstList.size();
        int secondLen = secondList.size();
        
        int minLen =  firstLen<= secondLen ? firstLen : secondLen;
        
        for(int i = 0; i < minLen; i++){
            resultList.add(firstList.get(i));
            resultList.add(secondList.get(i));
        }
        
        return resultList.stream();
        
    }        
}
