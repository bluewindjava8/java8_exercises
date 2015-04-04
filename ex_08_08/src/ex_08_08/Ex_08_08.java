
package ex_08_08;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Ex_08_08 {

    public static void main(String[] args) {
        testOrdinaryQueue();//普通のqueueで要素を取り出してCastする時まで違うクラスのインスタンスが混入したことが分からないことに対して、
        testCheckedQueue();//checkedQueueは違うクラスの要素をinsertする時に発見できる。エラーの早期発見に役に立つ。
    }
    
    public static void testOrdinaryQueue(){
        Queue<String> ordinaryQueue = new LinkedList<>();
        ordinaryQueue.add("Java");
        ordinaryQueue.add("Python");
        ordinaryQueue.add("C#");
        addIntegerToQueue(ordinaryQueue);
        
        for(String item : ordinaryQueue){
            System.out.println(item);
        }
        
    }
    
    public static void testCheckedQueue(){
        Queue<String> ordinaryQueue = new LinkedList<>();
        ordinaryQueue.add("Java");
        ordinaryQueue.add("Python");
        ordinaryQueue.add("C#");
        
        Queue<String> checkedQueue = Collections.checkedQueue(ordinaryQueue, String.class);
        addIntegerToQueue(checkedQueue);
        
        for(String item : ordinaryQueue){
            System.out.println(item);
        }
        
    }    
    
    public static void addIntegerToQueue(Queue queue){
        queue.add(new Integer(1));
    }
    
    
}
