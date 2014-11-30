
package ex_05_08;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;


public class Ex_05_08 {

    public static void main(String[] args) {
        Set<String> zoneIds = ZoneId.getAvailableZoneIds();
        Instant now = Instant.now();
        
        zoneIds.stream().map(zoneId -> ZonedDateTime.ofInstant(now, ZoneId.of(zoneId)))
                .forEach(zonedDateTime -> {
                    System.out.print(zonedDateTime.getZone() + " : ");
                    System.out.println(zonedDateTime.getOffset());
                });
        
        
    }
    
}
