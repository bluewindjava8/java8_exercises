
package ex_03_20;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Ex_03_20 {

    public static void main(String[] args) {

    }
    
    public static <T, U> List<U> map(List<T> list, Function<T, U> func){
        return list.stream().map(func).collect(Collectors.toList());
    }
}
