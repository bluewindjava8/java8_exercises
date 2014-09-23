package ex_1_9;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Predicate;

public interface Collection2<T> extends Collection<T>{
    default void forEachIf(Consumer<T> action, Predicate<T> predicate){
        //this.stream().filter(predicate).forEach(action);
        
        for (T elem : this){
            if(predicate.test(elem)){
                action.accept(elem);
            }
        }
    }
}
