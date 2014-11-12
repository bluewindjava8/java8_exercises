package ex_03_23;

import java.util.function.Function;

public class Ex_03_23 {

    public static void main(String[] args) {
        
    }
    
    
    public static <T, U> Pair<U> map(Pair<T> pair, Function<T, U> fun){
        U part = fun.apply(pair.getPart());
        U counterPart = fun.apply(pair.getCounterPart());
        
        return new Pair<>(part, counterPart);
    }
}
