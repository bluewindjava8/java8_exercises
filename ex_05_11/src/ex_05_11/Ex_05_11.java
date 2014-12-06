
package ex_05_11;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.chrono.ChronoPeriod;
import java.time.temporal.ChronoUnit;

public class Ex_05_11 {

    public static void main(String[] args) {
        ZoneId losZoneId = ZoneId.of("America/Los_Angeles");
        ZoneId cetZoneId = ZoneId.of("CET");
//        ZoneId losZoneId = ZoneId.of("Asia/Tokyo");
//        ZoneId cetZoneId = ZoneId.of("Asia/Shanghai");
        
        
        
        ZonedDateTime cetDateTime = ZonedDateTime.of(LocalDate.now(), LocalTime.of(14, 5, 0, 0), cetZoneId);
        ZonedDateTime losStartDateTime = cetDateTime.withZoneSameInstant(losZoneId);
        ZonedDateTime losArriveDateTime = ZonedDateTime.of(LocalDate.now(), LocalTime.of(16, 40, 0, 0), losZoneId);
//        System.out.println(cetDateTime);
//        System.out.println(losStartDateTime);
//        System.out.println(losArriveDateTime);
//        System.out.println(losStartDateTime.until(losArriveDateTime, ChronoUnit.MINUTES));
        long totalMinutes = losStartDateTime.until(losArriveDateTime, ChronoUnit.MINUTES);
        long hours = totalMinutes / 60;
        long minutes = totalMinutes % 60;
        System.out.printf("Duration: %d hours and %d minutes\n", hours, minutes);
    }
    
}
