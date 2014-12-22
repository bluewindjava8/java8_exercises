package ex_06_01;

import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

public class Ex_06_01 {

    public static void main(String[] args) {
        
    }
    
    public static String findLongestString(String[] words){
        AtomicReference<String> longestWord = new AtomicReference<>("");
        Stream.of(words).parallel().forEach(word -> {
            longestWord.updateAndGet(self -> self.length() >= word.length() ? self : word);
        });
        
        return longestWord.get();
    }
    
}
