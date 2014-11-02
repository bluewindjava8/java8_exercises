package test_03_07;




import ex_03_07.Order;
import java.util.Arrays;
import java.util.Comparator;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class Ex_03_07_Test {
    
    @Test
    public void testOrdinaryOrder(){
        assertEquals(1, 1);        
        String[] arrayToSort = {"abc", "acd", "abc", "efg", "12345"};
        String[] desiredArray = {"12345", "abc", "abc", "acd", "efg"};
        Comparator<String> comp = ex_03_07.Ex_03_07.generateComparator(new Order[]{Order.ORDINARY});
        Arrays.sort(arrayToSort, comp);
        assertArrayEquals(arrayToSort, desiredArray);
    }
    
    @Test
    public void testReverseOrder(){
        assertEquals(1, 1);        
        String[] arrayToSort = {"abc", "acd", "abc", "efg", "12345"};
        String[] desiredArray = {"12345", "abc", "abc", "acd", "efg"};
        Comparator<String> comp = ex_03_07.Ex_03_07.generateComparator(new Order[]{Order.REVERSE});
        Arrays.sort(arrayToSort, comp);
        System.out.println(Arrays.asList(arrayToSort));
        for(int i = 0; i < arrayToSort.length; i++){
            assertEquals(arrayToSort[i], desiredArray[arrayToSort.length - 1 - i]);
        }
        
    }  
    
    @Test
    public void testMixedCase(){
        assertEquals(1, 1);        
        String[] arrayToSort = {"abc", "acd", "Abc", "efg", "12345"};
        String[] desiredArray = {"12345", "Abc", "abc", "acd", "efg"};
        Comparator<String> comp = ex_03_07.Ex_03_07.generateComparator(new Order[]{Order.CASEINSENSITIVE,Order.CASESENSITIVE});
        Arrays.sort(arrayToSort, comp);
        System.out.println(Arrays.asList(arrayToSort));
        assertArrayEquals(arrayToSort, desiredArray);
        
    }
   
    
    @Test
    public void testNoWhiteSpaceAndMixedCase(){
        assertEquals(1, 1);        
        String[] arrayToSort = {"ab c", "acd", "A  bc", "ef    gpp", "kkk", "EF    gpp", "12345"};
        String[] desiredArray = {"12345", "A  bc", "ab c", "acd", "EF    gpp", "ef    gpp", "kkk"};
        Comparator<String> comp = ex_03_07.Ex_03_07.generateComparator(new Order[]{Order.NOWHITESPACE, Order.CASEINSENSITIVE,Order.CASESENSITIVE});
        Arrays.sort(arrayToSort, comp);
        System.out.println(Arrays.asList(arrayToSort));
        assertArrayEquals(arrayToSort, desiredArray);
        
    }
}
