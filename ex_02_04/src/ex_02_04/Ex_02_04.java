package ex_02_04;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Ex_02_04 {

    public static void main(String[] args) {
        int[] values = {1, 4, 9, 16};
        Stream<int[]> intArrayStream = Stream.of(values);//int[]のstreamになります。
        
        IntStream of = IntStream.of(values);
        //IntStream peek = of.peek(e -> System.out.println(e));
        //System.out.println(of);
        //System.out.println(peek);
        System.out.println(of.count());
    }
    
}
