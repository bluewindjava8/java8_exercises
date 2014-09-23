package ex_02_06;

import java.util.stream.IntStream;
import java.util.stream.Stream;


public class Ex_02_06 {

    public static void main(String[] args) {
        characterStream("12345abcde").forEach(System.out::println);
    }
    
    public static Stream<Character> characterStream(String s) {
        return IntStream.range(0, s.length()).boxed().map(s::charAt);
    }
}
