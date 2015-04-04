
package ex_08_07;

import java.util.Arrays;
import java.util.Comparator;
import static java.util.Comparator.*;


public class Ex_08_07 {


    public static void main(String[] args) {
        //Comparator<String> comp1= comparing(s -> s, nullsFirst(naturalOrder()).reversed());
        //Comparator<Person> comp1 = comparing(Person::getMiddleName, nullsFirst( naturalOrder()).reversed());
        Comparator<Person> comp = comparing(Person::getMiddleName, nullsLast(reverseOrder()));
        
        Person[] people = new Person[]{ new Person("Mickle", "Jackson"), new Person("Mickel", "Jordan"),
                                        new Person("Tom", "Cruise", "pp"), new Person("Jerry", "Alf", "Anold"),
                                        new Person("Mao", "ze", "dong")};
        System.out.println("Before sort.");
        System.out.println(Arrays.asList(people));
        Arrays.sort(people, comp);
        System.out.println("Afger sort.");
        System.out.println(Arrays.asList(people));        
        
    }
    
}

class Person{
    private String firstName;
    private String lastName;
    private String middleName;
    
    public Person(String fName, String lName){
        this(fName, lName, null);
    }
        
    public Person(String fName, String lName, String mName){
        firstName = fName;
        lastName = lName;
        middleName = mName;
    }
    
    
    
    public String getMiddleName(){
        return middleName;
    }
    
    public String toString(){
        return middleName;
    }
}