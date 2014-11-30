package Test_05_01;

import ex_05_01.Ex_05_01;
import java.time.LocalDate;
import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class Test_05_01 {
    @Test
    public void Test100YearsOfProgrammersDay(){
        
        for(int year = 2000; year < 2100; year++){
            LocalDate programmersDay = Ex_05_01.getProgrammersDay(year);
            LocalDate desiredProgrammersDay = LocalDate.of(year, 1, 1).plusDays(255);
            assertEquals(programmersDay, desiredProgrammersDay);
        }

    }
}
