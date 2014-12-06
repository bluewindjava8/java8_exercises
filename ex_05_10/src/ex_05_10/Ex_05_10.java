
package ex_05_10;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Ex_05_10 {


    public static void main(String[] args) {
        ZoneId losZoneId = ZoneId.of("America/Los_Angeles");
        ZoneId cetZoneId = ZoneId.of("CET");
//        ZoneId losZoneId = ZoneId.of("Asia/Tokyo");
//        ZoneId cetZoneId = ZoneId.of("Asia/Shanghai");
        
        
        
        ZonedDateTime losDateTime = ZonedDateTime.of(LocalDate.now(), LocalTime.of(3, 5, 0, 0), losZoneId);
        ZonedDateTime cetDateTime = losDateTime.withZoneSameInstant(cetZoneId);
//        System.out.println(losDateTime);
//        System.out.println(cetDateTime);
        System.out.println("Arrive at : " + cetDateTime.plusHours(10).plusMinutes(50));
    }
    
}
