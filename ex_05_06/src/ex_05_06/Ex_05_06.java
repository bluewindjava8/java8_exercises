
package ex_05_06;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;


public class Ex_05_06 {


    public static void main(String[] args) {
        LocalDate start = LocalDate.of(1901, 1, 1);
        LocalDate end = LocalDate.of(2000, 12, 31);
        start = start.with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY));
        for(LocalDate date = start; !date.isAfter(end); date = date.plusDays(7)){
            if(date.getDayOfMonth() == 13){
                System.out.println(date);
            }
        }
        
    }
    
}
