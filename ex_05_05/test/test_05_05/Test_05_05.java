
package test_05_05;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Test_05_05 {
    @Test
    public void testBirthday(){
        LocalDate start = LocalDate.of(2000, 1, 1);
        LocalDate end = LocalDate.now();
        
        LocalDate baseDate = LocalDate.of(2014, 11, 30);
        
        long desiredDays = 5447 + baseDate.until(end, ChronoUnit.DAYS);
        assertEquals(desiredDays, ex_05_05.Ex_05_05.getDaysBetween(start, end));
    }
}
