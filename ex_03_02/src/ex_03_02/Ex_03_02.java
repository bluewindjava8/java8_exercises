
package ex_03_02;

import java.util.concurrent.locks.ReentrantLock;

public class Ex_03_02 {

    public static void main(String[] args) {
        test();
    }
    
    public static void test(){
        ReentrantLock lock = new ReentrantLock();
        withLock(lock, ()->System.out.println("do something."));
    }
    
    public static void withLock(ReentrantLock lock, Runnable r){
        lock.lock();
        try{
            r.run();
        }finally{
            lock.unlock();
        }
    }
}
