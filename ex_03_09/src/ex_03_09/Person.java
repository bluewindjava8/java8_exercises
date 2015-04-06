
package ex_03_09;

import java.lang.reflect.Field;
import java.security.InvalidParameterException;
import java.util.Comparator;
import java.util.function.Function;

public class Person{
    private String lastName;
    private String firstName;
    private int age;
    
    public Person(String lName, String fName, int age){
        lastName = lName;
        firstName = fName;
        this.age = age;
    }
    
    public String toString(){
        return firstName + " " + lastName + "-" + age;
    }
    
    public static Comparator<Person> lexicographicComparator(String... fieldNames){
        if(fieldNames == null || fieldNames.length == 0){
            throw new InvalidParameterException("fieldNames is invalid.");
        }
        
        Comparator<Person> comp = getComparatorByFieldName(fieldNames[0]);
        //Comparator<Person> fcomp = Comparator.comparing(person -> person.getFieldValueByFieldName(fieldNames[0]));
        
        for(int i = 1; i < fieldNames.length; i++){
            comp = comp.thenComparing(getComparatorByFieldName(fieldNames[i]));                
        }
        
        return comp;
    }
    
    private Comparable getFieldValueByFieldName(String fieldName){
        Field field;
        try {
            field = Person.class.getDeclaredField(fieldName);
            //field.setAccessible(true);
            Object value = field.get(this);
            return (Comparable)value;
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
            throw new InvalidParameterException("No such field");
        }

    }
    
    private static Comparator<Person> getComparatorByFieldName(String fieldName){
        return (p1, p2) -> p1.getFieldValueByFieldName(fieldName).compareTo(p2.getFieldValueByFieldName(fieldName));
    }
    
}

class Test{
    public static void test(){
        Comparable c = (Comparable) new Test();
        Function f = (Function) new Test();
        //Test t = (Test)new Person();
    }
}

