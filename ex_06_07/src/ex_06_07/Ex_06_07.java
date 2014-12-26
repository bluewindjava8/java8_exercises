package ex_06_07;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Ex_06_07 {

    public static void main(String[] args) {

    }
    
    public static String findKeyOfMaxValue(ConcurrentHashMap<String, Long> map){
        Map.Entry<String, Long> entry = 
                map.reduceEntries(1, (entry1, entry2) -> entry1.getValue() >= entry2.getValue() ? entry1 : entry2);
        return entry.getKey();
    }
}
