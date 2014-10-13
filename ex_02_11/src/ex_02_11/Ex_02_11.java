package ex_02_11;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import temp.Employee;
import temp.temp1.Person;

public class Ex_02_11 {

    public static void main(String[] args) {
        List<String> strList = new ArrayList<>();
        strList.add("tom");strList.add("jerry");
        strList.stream().count();
        boolean b = strList.stream().anyMatch(s -> s.length() > 3);
        Stream<String> stream = strList.stream();
        Stream<String> stream2 = stream.peek(System.out::println);
//        Stream<String> stream3 = stream.peek(System.out::println);
        //stream.count();
        //b = stream.anyMatch(s -> s.length() > 3);
        
        Base base = new Base();
        Derive d = new Derive();
        base.fun(d);
        d.fun(d);
        
        int kkk = 0;

        
        Comparator<TestClass> comp1 =  (t1, t2) -> {int i = kkk; return t1.test(t2);};

        comp1.compare(new TestClass(), new TestClass());
        
//        Comparator<TestClass> comp2 = (t1, t2) -> TestClass.test(t1, t2);
//        comp2.compare(new TestClass(), new TestClass());
        
        Comparator<TestClass> comp3 = TestClass::test;
        comp3.compare(new TestClass(), new TestClass());
        
        Stream<String> s1 = Stream.of("abc", "defg");
        Stream<String> s2 = Stream.generate(()->"hello");
        Stream<String> s3 = Stream.of("kk");
        
//        Stream<String> s4 = Stream.concat(s1, s3);
//        System.out.println(s4.count());
        
        //Stream<String> s5 = Stream.concat(s1, s2);
        Stream<String> s5 = Stream.concat(s2, s1);
        
        //Optional<String> opt = Optional.of(null);
        //System.out.println(opt);
        
        Stream<Employee> empStream = Stream.of(new Employee(), new Employee());
        empStream.filter(e -> e.isAlive()).count();
        System.out.println();
        
        Employee.fun();
        
        
    }
    
    
    
//    public static List<String> collect(Stream<String> stream){
//        List<String> list = Collections.synchronizedList(new ArrayList<String>());
//        
//        
//    }
    
    public static int[] split(int length, int n){
        if( n <= 0){
            throw new InvalidParameterException("Can not be splitted to n part(s).");
        }
        if(length <= 0){
            throw new InvalidParameterException("length Can not be " + length);
        }
        
        
        if (length <= n){
            n = length;
            int[] lens = new int[n];
            for(int i = 0; i < n; i++){
                lens[i] = 1;
            }
            return lens;
        }else{
            int size = length / n;
            int mod = length % n;
            int[] lens = new int[n];
            for( int i = 0; i < n; i++){
                lens[i] = size;
                if( i == n - 1){
                    lens[i] += mod;
                }
            }
            return lens;  
        }
        
    }
    
    public static void printIntArray(int[] intArray){
        for(int i = 0; i < intArray.length; i++){
            System.out.println("intArray[ " + i +" ] = " + intArray[i]);
        }
        System.out.println();
    }
    
    public static void testSplit(){        
        printIntArray(split(100, 5));
        printIntArray(split(102, 5));        
        printIntArray(split(10, 5));     
        printIntArray(split(13, 5)); 
        printIntArray(split(9, 5)); 
        printIntArray(split(6, 5)); 
        printIntArray(split(4, 5)); 
    }
    
}

class TestClass{
    int test(TestClass i){
        System.out.println("instance method.");
        return 0;
    }
    
//    static int test(TestClass t1, TestClass t2){
//        System.out.println("static method.");
//        return 1;
//    }
}





class Base{
    public static int m;
    final private void test(){
        System.out.println("private Base.test");
    }
    
    public void fun(Base b){
        b.test();
    }
    
    public boolean decide(String str){
        return true;
    }
}

class Derive extends Base{
    //public int m;
    public void test(){
        System.out.println("public Derive.test");
        System.out.println(Base.m);
        
    }
}