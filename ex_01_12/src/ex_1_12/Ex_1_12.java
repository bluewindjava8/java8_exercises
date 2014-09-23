package ex_1_12;

import java.util.Collection;
import java.util.Iterator;

public class Ex_1_12 {

    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}


interface Testable{
    //このdefault methodの追加によって、39行でコンパイラエラーを発生させます。
//    default void test(){
//        
//    }
}

class Outer {
    public void test(int i){
        
    }
    
    class Derive implements Testable{
        public void fun(){
            test(3);
        }
    }
    
}

class OuterClass<E> {
    public void stream(int i){

    }
    
    class Derive<E> implements Collection<E>{

        public void test(){
            //OuterClass.this.stream(1);
            stream(1);
        }
        
        @Override
        public int size() {
            throw new UnsupportedOperationException("Not supported yet.");  
        }

        @Override
        public boolean isEmpty() {
            throw new UnsupportedOperationException("Not supported yet.");  
        }

        @Override
        public boolean contains(Object o) {
            throw new UnsupportedOperationException("Not supported yet.");  
        }

        @Override
        public Iterator<E> iterator() {
            throw new UnsupportedOperationException("Not supported yet.");  
        }

        @Override
        public Object[] toArray() {
            throw new UnsupportedOperationException("Not supported yet.");  
        }

        @Override
        public <T> T[] toArray(T[] a) {
            throw new UnsupportedOperationException("Not supported yet.");  
        }

        @Override
        public boolean add(E e) {
            throw new UnsupportedOperationException("Not supported yet.");  
        }

        @Override
        public boolean remove(Object o) {
            throw new UnsupportedOperationException("Not supported yet.");  
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            throw new UnsupportedOperationException("Not supported yet.");  
        }

        @Override
        public boolean addAll(Collection<? extends E> c) {
            throw new UnsupportedOperationException("Not supported yet.");  
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            throw new UnsupportedOperationException("Not supported yet.");  
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            throw new UnsupportedOperationException("Not supported yet.");  
        }

        @Override
        public void clear() {
            throw new UnsupportedOperationException("Not supported yet.");  
        }
        
    }
    
}