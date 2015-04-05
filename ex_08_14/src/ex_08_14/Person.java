
package ex_08_14;

import java.util.Objects;

public class Person {

    private String name;
    private int age;
    
    public Person(String name, int age){
        this.name = Objects.requireNonNull(name, () -> "name field should not be null while age is "+ age);
        this.age = age;
        
    }
}
