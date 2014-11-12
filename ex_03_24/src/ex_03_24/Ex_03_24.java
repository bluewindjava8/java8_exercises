
package ex_03_24;

import java.util.function.Function;

public class Ex_03_24 {

    public static void main(String[] args) {
        
    }
    
    
    public static <T, U> Pair<U> flatMap(Pair<T> pair, Function<? super T, ? extends Pair<? extends U>> func ){
        Pair<U> pair1 = (Pair<U>) func.apply(pair.getPart());
        Pair<U> pair2 = (Pair<U>) func.apply(pair.getCounterPart());
        
        //意味上は 二つのPair<U>は一つのPair<U>に合成できません。数あわないからです。
        return null;
        
    }
}
