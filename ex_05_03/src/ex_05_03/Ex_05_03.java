
package ex_05_03;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.util.function.Predicate;

public class Ex_05_03 {


    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        LocalDate nextWorkDay = today.with(next(w -> w.getDayOfWeek().getValue() < 6));
        System.out.println(nextWorkDay);
    }
 
    public static  TemporalAdjuster next(Predicate<LocalDate> condition){
        return date -> {
            LocalDate result = (LocalDate) date;
            do{
                result = result.plusDays(1);
                if(condition.test(result)){
                    return result;
                }
            }while(true);
            
            //throw new RuntimeException("should not be here");
        };
    }
}
