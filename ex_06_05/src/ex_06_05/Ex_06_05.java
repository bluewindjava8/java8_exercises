package ex_06_05;

import java.util.concurrent.ConcurrentHashMap;

public class Ex_06_05 {

    public static void main(String[] args) {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        
        map.merge("aaa", 1, (existingValue, newValue) -> existingValue + newValue);
        System.out.println(map.get("aaa"));
        map.merge("aaa", 1, (existingValue, newValue) -> existingValue + newValue);
        System.out.println(map.get("aaa"));
        map.merge("aaa", 10, (existingValue, newValue) -> existingValue + newValue);
        System.out.println(map.get("aaa"));
        
    }
    
}
