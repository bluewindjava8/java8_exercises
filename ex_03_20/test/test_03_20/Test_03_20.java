package test_03_20;

import static ex_03_20.Ex_03_20.map;
import ex_03_20.Person;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;


/**
 *
 * @author bluewind
 */
public class Test_03_20 {
    @Test
    public void testStringToInteger(){
        List<String> names = new LinkedList<>();
        names.add("Tom");
        names.add("Jerry");
        names.add("Obama");
        names.add("Lincon");
        
        List<Integer> lengths = map(names, String::length);
        System.out.println(lengths);
        
        List<Integer> desiredList = Arrays.asList(new Integer[]{3, 5, 5, 6});
        assertEquals(desiredList, lengths);
    }
    
    @Test
    public void testPersonToLastName(){
        Person p1 = new Person("Mical", "Jackson", 52);
        Person p2 = new Person("Mical", "Jodan", 20);
        Person p3 = new Person("Bruese", "Tom", 51);
        Person p4 = new Person("Cluse", "Tom", 55);
        
        List<Person> persons = new LinkedList<>();
        persons.add(p1);
        persons.add(p2);
        persons.add(p3);
        persons.add(p4);
        
        List<String> lastNames = map(persons, Person::getLastName);
        System.out.println(lastNames);
        
        List<String> desiredList = Arrays.asList(new String[]{p1.getLastName(), 
            p2.getLastName(), p3.getLastName(), p4.getLastName()});
        
        assertEquals(desiredList, lastNames);
    }
}

