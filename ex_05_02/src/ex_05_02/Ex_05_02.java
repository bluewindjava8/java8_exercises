
package ex_05_02;

import java.time.LocalDate;

public class Ex_05_02 {

    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2000, 2, 29);
        System.out.println(date.plusYears(1));
        System.out.println(date.plusYears(4));
        System.out.println(date.plusYears(1).plusYears(1).plusYears(1).plusYears(1));
        
    }
    
}
