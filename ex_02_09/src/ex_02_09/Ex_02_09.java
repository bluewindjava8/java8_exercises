package ex_02_09;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

public class Ex_02_09 {

    public static void main(String[] args) {

        ArrayList<String> list1 = new ArrayList<>();
        list1.add("12");list1.add("34");list1.add("56");
        
        ArrayList<String> list2 = new ArrayList<>();
        list2.add("ab");list2.add("cd");list2.add("ef");list2.add("gh");
        
        ArrayList<String> list3 = new ArrayList<>();
        list2.add("AB");list2.add("CD");list2.add("EF");list2.add("GH");        
        
        ArrayList resultList1 = convert1(Stream.of(list1, list2, list3));
        ArrayList resultList2 = convert2(Stream.of(list1, list2, list3));    
        ArrayList resultList3 = convert3(Stream.of(list1, list2, list3));
        
        System.out.println(resultList1);
        System.out.println(resultList2);
        System.out.println(resultList3);
        
    }
    
    public static <T> ArrayList<T> convert1(Stream<ArrayList<T>> stream){
        Optional<ArrayList<T>> result = stream.reduce((x, y) -> {ArrayList<T> z = new ArrayList<>(); z.addAll(x); z.addAll(y); return z;});
        
        return result.isPresent()? result.get() : new ArrayList<>();
    }
    
    public static <T> ArrayList<T> convert2(Stream<ArrayList<T>> stream){
        ArrayList<T> result = stream.reduce(new ArrayList<>(), (x, y) -> {ArrayList<T> z = new ArrayList<>(); z.addAll(x); z.addAll(y); return z;});
        
        return result;
    }
    
    public static <T> ArrayList<T> convert3(Stream<ArrayList<T>> stream){
        ArrayList<T> result = stream.reduce(new ArrayList<>(), (x, y) -> {ArrayList<T> z = new ArrayList<>(); z.addAll(x); z.addAll(y); return z;}, (x, y) -> {x.addAll(y); return x;} );
        
        return result;
    }   
}
