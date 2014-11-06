package ex_03_20;

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
 
    public String getLastName(){
        return lastName;
    }
    
}