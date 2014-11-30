
package ex_05_05;

import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class Ex_05_05 {


    public static void main(String[] args) {
//        LocalDate start = LocalDate.of(2014, 11, 30);
//        LocalDate end = LocalDate.of(2014, 12, 1);
        
        LocalDate start = LocalDate.of(2000, 1, 1);
        LocalDate end = LocalDate.now();
        System.out.println(getDaysBetween(start, end));
        //System.out.println(getDaysBetween(start, end));        
    }
    
    public static long getDaysBetween(LocalDate start, LocalDate end){
        if(start.isAfter(end)){
            throw new InvalidParameterException("start is later than end");
        }
        
        return start.until(end, ChronoUnit.DAYS);
    }
}
