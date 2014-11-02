package test_03_09;

import ex_03_09.Person;
import java.util.Arrays;
import java.util.Comparator;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
public class Test_03_09 {
    Person p1 = new Person("Mical", "Jackson", 52);
    Person p2 = new Person("Mical", "Jodan", 20);
    Person p3 = new Person("Bruese", "Tom", 51);
    Person p4 = new Person("Cluse", "Tom", 55);   
    Person p5 = new Person("Cluse", "Tom", 53);
    Person p6 = new Person("Gates", "Bill", 57);
    Person p7 = new Person("Cluse", "Tom", 55); 
    
    Person[] persons = new Person[]{p1, p2, p3, p4, p5, p6, p7};
    
    
    @Test
    public void TestOneField(){
        Comparator<Person> comp = Person.lexicographicComparator("lastName");
        assertEquals(0, comp.compare(p1, p2));
        assertTrue(comp.compare(p1, p3) > 0);
    }
    
    @Test
    public void TestTwoFields(){
        Comparator<Person> comp = Person.lexicographicComparator("lastName", "firstName");
        assertTrue(comp.compare(p1, p2) < 0);
        assertTrue(comp.compare(p1, p3) > 0);
        assertTrue(comp.compare(p4, p5)== 0);        
    }
    
    @Test
    public void TestThreeFields(){
        Comparator<Person> comp = Person.lexicographicComparator("lastName", "firstName", "age");
        assertTrue(comp.compare(p1, p2) < 0);
        assertTrue(comp.compare(p1, p3) > 0);
        assertTrue(comp.compare(p4, p5) > 0);
        assertTrue(comp.compare(p4, p7)== 0);        
    }    
}
