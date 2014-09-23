
package ex_02_05;

import java.util.List;
import java.util.stream.Stream;

public class Ex_02_05 {

    public static void main(String[] args) {
        genRandomStream(25214903917L, 11, (long)Math.pow(2, 48), 0).limit(5).forEach(System.out::println);
    }
    
    public static Stream<Long> genRandomStream(long a, long c, long m, long seed) {
        return Stream.iterate(seed, e -> (a * e + c) % m);

    }
}
