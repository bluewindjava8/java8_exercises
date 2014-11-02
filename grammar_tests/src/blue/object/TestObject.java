/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package blue.object;

import java.util.Comparator;
import java.util.stream.Stream;

/**
 *
 * @author bluewind
 */
public class TestObject implements Test{
    public static void main(String[] args){
        Comparator<String> c = new Comparator<String>(){

            @Override
            public int compare(String o1, String o2) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            
        };
        
        Comparator<String> c2 = (s1, s2)-> s1.length() - s2.length();
        System.out.println(c2.getClass().getCanonicalName());
        
        //System.out.println((s1, s2)-> s1.length() - s2.length(););        
        
        
        
        System.out.println(c.getClass().getCanonicalName());
        System.out.println(new Comparator<String>(){

            @Override
            public int compare(String o1, String o2) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            
        }.getClass().getCanonicalName());        
        
        Object o1 = c;
        Object o2 = new Comparator<String>(){

            @Override
            public int compare(String o1, String o2) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            
        };
        
        Stream<String> s1 = Stream.of("aaa", "bbb");
        s1.filter(s->s.length() > 10);
        //s1.filter(s->s.length() > 12);
        
        Stream<Double> doubleStream = Stream.generate(Math::random);
        doubleStream.sorted().forEach(System.out::println);
        
        //Object o3 = 
    }
    
    
//    public static void test(int x, int k){
//        
//    }
    
    public void test(int y, int z){
        //TestObject.test(8,9);
        Test.test(8, 9);
    }
}


interface Test{
    public static void test(int y, int z){
        
    }
}

//@FunctionalInterface
//interface Playable{
//    void play();
//    void aa();
//    
//    int i = 0;
//    
//    static void foo(){
//        
//    }
//}