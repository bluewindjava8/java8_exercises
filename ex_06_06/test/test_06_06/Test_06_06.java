

package test_06_06;


import static ex_06_06.Ex_06_06.*;
import java.io.File;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import static org.junit.Assert.assertEquals;
import org.junit.Test;


public class Test_06_06 {

    @Test
    public void test() throws InterruptedException{
        File file1 = new File("file1.txt");
        File file2 = new File("file2.txt");
        File file3 = new File("file3.txt");
        
                
        File[] files = new File[]{file1, file2, file3};
        Map<String, Set<File>> map = classifyWords(files);
        
        Set<File> kaSet = map.get("ka");
        Set<File> expectedSet = new HashSet<>();
        expectedSet.add(file1);
        expectedSet.add(file2);
        expectedSet.add(file3);
        assertEquals(kaSet, expectedSet);
        
        Set<File> aSet = map.get("a");
        expectedSet.clear();
        expectedSet.add(file2);
        expectedSet.add(file3);
        assertEquals(aSet, expectedSet); 
        
        Set<File> ASet = map.get("A");
        expectedSet.clear();
        expectedSet.add(file3);
        assertEquals(ASet, expectedSet);         
    }
    
}
