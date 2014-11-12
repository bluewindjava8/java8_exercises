package ex_03_21;

public class Person{
    private String name;
    private int age;
    
    public Person(String name, int age){
        this.name = name;
        this.age = age;
    }
    
    public String toString(){
        return name + " is " + age;
    }
}
