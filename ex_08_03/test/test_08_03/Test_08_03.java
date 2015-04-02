
package test_08_03;

import org.junit.Test;
import ex_08_03.Ex_08_03;
import ex_08_03.Remainder;
import ex_08_03.RemainderType;

import static org.junit.Assert.assertEquals;

public class Test_08_03 {
    
    @Test
    public void testBothPositive(){
        int gcd1 = Ex_08_03.gcd(21, 14, Remainder.getRemainder(RemainderType.TYPE_ORDINARY));
        int gcd2 = Ex_08_03.gcd(21, 14, Remainder.getRemainder(RemainderType.TYPE_FLOORMOD));
        int gcd3 = Ex_08_03.gcd(21, 14, Remainder.getRemainder(RemainderType.TYPE_NOMINUS));
        
        assertEquals(gcd1, 7);
        assertEquals(gcd2, 7);
        assertEquals(gcd2, 7);   
        
        
        int gcd4 = Ex_08_03.gcd(1071, 462, Remainder.getRemainder(RemainderType.TYPE_ORDINARY));
        int gcd5 = Ex_08_03.gcd(1071, 462, Remainder.getRemainder(RemainderType.TYPE_FLOORMOD));
        int gcd6 = Ex_08_03.gcd(1071, 462, Remainder.getRemainder(RemainderType.TYPE_NOMINUS));    
        
        assertEquals(gcd4, 21);
        assertEquals(gcd5, 21);
        assertEquals(gcd6, 21); 
        
    }
    
    @Test
    public void testBothMinus(){
        int gcd1 = Ex_08_03.gcd(-21, -14, Remainder.getRemainder(RemainderType.TYPE_ORDINARY));
        int gcd2 = Ex_08_03.gcd(-21, -14, Remainder.getRemainder(RemainderType.TYPE_FLOORMOD));
        int gcd3 = Ex_08_03.gcd(-21, -14, Remainder.getRemainder(RemainderType.TYPE_NOMINUS));
        
        assertEquals(gcd1, 7);
        assertEquals(gcd2, 7);
        assertEquals(gcd2, 7);   
        
        
        int gcd4 = Ex_08_03.gcd(-1071, -462, Remainder.getRemainder(RemainderType.TYPE_ORDINARY));
        int gcd5 = Ex_08_03.gcd(-1071, -462, Remainder.getRemainder(RemainderType.TYPE_FLOORMOD));
        int gcd6 = Ex_08_03.gcd(-1071, -462, Remainder.getRemainder(RemainderType.TYPE_NOMINUS));    
        
        assertEquals(gcd4, 21);
        assertEquals(gcd5, 21);
        assertEquals(gcd6, 21); 
        
    }
    
    @Test
    public void testFirstMinus(){
        int gcd1 = Ex_08_03.gcd(-21, 14, Remainder.getRemainder(RemainderType.TYPE_ORDINARY));
        int gcd2 = Ex_08_03.gcd(-21, 14, Remainder.getRemainder(RemainderType.TYPE_FLOORMOD));
        int gcd3 = Ex_08_03.gcd(-21, 14, Remainder.getRemainder(RemainderType.TYPE_NOMINUS));
        
        assertEquals(gcd1, 7);
        assertEquals(gcd2, 7);
        assertEquals(gcd2, 7);   
        
        
        int gcd4 = Ex_08_03.gcd(-1071, 462, Remainder.getRemainder(RemainderType.TYPE_ORDINARY));
        int gcd5 = Ex_08_03.gcd(-1071, 462, Remainder.getRemainder(RemainderType.TYPE_FLOORMOD));
        int gcd6 = Ex_08_03.gcd(-1071, 462, Remainder.getRemainder(RemainderType.TYPE_NOMINUS));    
        
        assertEquals(gcd4, 21);
        assertEquals(gcd5, 21);
        assertEquals(gcd6, 21); 
        
    }
        
    @Test
    public void testSecondMinus(){
        int gcd1 = Ex_08_03.gcd(21, -14, Remainder.getRemainder(RemainderType.TYPE_ORDINARY));
        int gcd2 = Ex_08_03.gcd(21, -14, Remainder.getRemainder(RemainderType.TYPE_FLOORMOD));
        int gcd3 = Ex_08_03.gcd(21, -14, Remainder.getRemainder(RemainderType.TYPE_NOMINUS));
        
        assertEquals(gcd1, 7);
        assertEquals(gcd2, 7);
        assertEquals(gcd2, 7);   
        
        
        int gcd4 = Ex_08_03.gcd(1071, -462, Remainder.getRemainder(RemainderType.TYPE_ORDINARY));
        int gcd5 = Ex_08_03.gcd(1071, -462, Remainder.getRemainder(RemainderType.TYPE_FLOORMOD));
        int gcd6 = Ex_08_03.gcd(1071, -462, Remainder.getRemainder(RemainderType.TYPE_NOMINUS));    
        
        assertEquals(gcd4, 21);
        assertEquals(gcd5, 21);
        assertEquals(gcd6, 21); 
        
    }   
}
