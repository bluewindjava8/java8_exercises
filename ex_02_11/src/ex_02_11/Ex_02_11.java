package ex_02_11;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Stream;


public class Ex_02_11 {

    public static void main(String[] args){
        test();
        
    }
    
    
    public static void test(){
        int size1 = 2;
        Stream<String> stream1 = Stream.of("abc","def").parallel();
        
        int size2 = 1000;
        Stream<String> stream2 = Stream.generate(()->"blue").limit(size2).parallel();
        
        int size3 = 1000;
        Stream<Integer> stream3 = Stream.iterate(0, n -> n + 1).limit(size3).parallel();
        
//        ArrayList<String> list1 = collectToSingleArrayList(stream1, size1);
//        System.out.println(list1);
//        System.out.println("size = " + list1.size() +"\n");
        
        ArrayList<String> list2 = collectToSingleArrayList(stream2, size2);
        System.out.println(list2);
        System.out.println("size = " + list2.size() +"\n");
        
        
        ArrayList<Integer> list3 = collectToSingleArrayList(stream3, size3);
        System.out.println(list3);
        System.out.println("size = " + list3.size() +"\n");      
    }
    
    //Problem: The original order is not kept. (It seems that the order is not necessary)
    public static <T> ArrayList<T> collectToSingleArrayList(Stream<T> stream, int size){
        AtomicInteger counter = new AtomicInteger(0);
        ReentrantLock lock = new ReentrantLock();
        ArrayList<T> list = new ArrayList<>(size);
         
        //stream.peek(System.out::println).forEach(t -> list.add(counter.getAndIncrement(), t));
        stream.forEach(t -> 
                                {
                                    lock.lock();
                                    try{
                                        list.add(t);
                                    }finally{
                                        lock.unlock();
                                    }
                                    
                                });
        return list;
    }
    
}
    
    
    
