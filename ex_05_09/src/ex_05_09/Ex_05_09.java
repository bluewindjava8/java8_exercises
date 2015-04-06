
package ex_05_09;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;

public class Ex_05_09 {


    public static void main(String[] args) {
                Set<String> zoneIds = ZoneId.getAvailableZoneIds();
        Instant now = Instant.now();
        
        zoneIds.stream().map(zoneId -> ZonedDateTime.ofInstant(now, ZoneId.of(zoneId)))
                .filter(zonedDateTime -> Math.abs(zonedDateTime.getOffset().getTotalSeconds()) % 3600 != 0 )
                .forEach(zonedDateTime -> {
                    System.out.print(zonedDateTime.getZone() + " : ");
                    System.out.println(zonedDateTime.getOffset());
                });
    }
    
}
