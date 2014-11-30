package ex_05_04;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class Ex_05_04 {

    public static void main(String[] args) {
//        for(int i = 1; i < 31; i++){
//            System.out.printf("%2d\t", i);
//            if(i % 7 == 0){
//                System.out.println();
//            }
//        }
        
        printMonthCal(2014, 11);
        printMonthCal(2014, 12);        
        printMonthCal(2013, 3);
        printMonthCal(2015, 2);   
        printMonthCal(1977, 8);
        
    }
    
    public static void printMonthCal(int year, int month){

        LocalDate firstDay = LocalDate.of(year, month, 1);
        System.out.print(year + " - " + firstDay.getMonth() + "\n");
        for(int i = 0; i < firstDay.getDayOfWeek().getValue() - 1; i++){
            System.out.printf("%2s\t", " ");
        }
        
        LocalDate lastDay = firstDay.with(TemporalAdjusters.lastDayOfMonth());
        for(LocalDate date = firstDay; !date.isAfter(lastDay); date = date.plusDays(1)){
            System.out.printf("%2d\t", date.getDayOfMonth());
            if(date.getDayOfWeek().getValue() % 7 == 0){
                System.out.println();
            }
        }
        System.out.println();
    }
        
}
