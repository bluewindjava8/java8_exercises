package temp;

import java.util.function.Predicate;
import temp.temp1.Person;

public class Employee extends Person{
        @Override
    public boolean isAlive(){
        System.out.println("Employee.isAlive.");
        return true;
    }
    
    public static void fun(){
        test1(Person::isAlive);
        test1(Employee::isAlive);
        test1((p) -> p.isAlive());
        test2(Person::isAlive);
        test2(Employee::isAlive);  
        test2((Person p) -> p.isAlive());
    }
    
    public static void test1(Predicate<Employee> pred){
        pred.test(new Employee());
        //pred.test(new Person());
    }
    
    public static void test2(Predicate<? super Employee> pred){
        pred.test(new Employee());
        //pred.test(new Person());
    }    
}
