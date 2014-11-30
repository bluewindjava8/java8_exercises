package test_05_03;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Test_05_03 {
    @Test
    public void testNext31(){
        LocalDate srcDate = LocalDate.of(2014, 11, 1);
        TemporalAdjuster ajuster = ex_05_03.Ex_05_03.next(w -> w.getDayOfMonth() == 31);
        LocalDate destDate = srcDate.with(ajuster);
        assertEquals(destDate, LocalDate.of(2014, 12, 31));
    }
    
    @Test
    public void testFirstDayAfterTenYears(){
        LocalDate srcDate = LocalDate.of(2014, 11, 1);
        TemporalAdjuster ajuster = ex_05_03.Ex_05_03.next(
                w -> w.getDayOfYear() == 1 && w.getYear() == srcDate.getYear() + 10);
        LocalDate destDate = srcDate.with(ajuster);
        assertEquals(destDate, LocalDate.of(2024, 1, 1));
    }
}