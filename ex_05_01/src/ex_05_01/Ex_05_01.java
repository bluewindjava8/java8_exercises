
package ex_05_01;

import java.time.LocalDate;

public class Ex_05_01 {

    public static void main(String[] args) {
        LocalDate programmersDay = LocalDate.of(2014, 1, 1).plusDays(255);
        System.out.println(programmersDay);
        programmersDay = getProgrammersDay(2014);
        System.out.println(programmersDay);
    }
    
    public static LocalDate getProgrammersDay(int year){
        return LocalDate.of(year, 1, 1).withDayOfYear(256);
    }
    
}
