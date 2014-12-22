package test_06_01;

import org.junit.Test;
import static ex_06_01.Ex_06_01.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.junit.Assert.assertEquals;


public class Test_06_01 {
    @Test
    public void testFewStrings(){
        String[] strs = {"a", "bc", "defg", "hijkl", "123r545", "sdfwerwete935", "2", "w4wfreyty6upo345456kg", "ddddddd"};
        String result = findLongestString(strs);
        assertEquals(result, "w4wfreyty6upo345456kg");
    }
    
    @Test
    public void testManyStrings() throws IOException{
        String contents = new String(Files.readAllBytes(Paths.get("install.log")), StandardCharsets.UTF_8);
        String[] words = contents.split("[\\P{L}]+");
        String result = findLongestString(words);
        assertEquals(result, "SecureBackupRecoveryRequiresVerificationTokenbluewind");
        
    }
}
