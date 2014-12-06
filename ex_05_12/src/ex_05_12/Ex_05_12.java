package ex_05_12;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class Ex_05_12 {

    private List<String> schedules;

    public static void main(String[] args) {
        
        Schedule schedule = new Schedule(Paths.get("schedule.txt"));        
        ScheduledExecutorService service = Executors.newScheduledThreadPool(10);

        long initialDelay = 0;
        long period = 60;
        //Instant oneHourLater = Instant.now().plusSeconds(3600);
        
        service.scheduleAtFixedRate(
                ()->{ 
                        System.out.println("The following item(s) will start in an hour:");
                        schedule.getScheduleItems().filter(item -> item.willStartAt(Instant.now().plusSeconds(3600)))
                        .forEach(System.out::println);
                        System.out.println();
                }, 
                initialDelay, period, TimeUnit.SECONDS);
    }

}
