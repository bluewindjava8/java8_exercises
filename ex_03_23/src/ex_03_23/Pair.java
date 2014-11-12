package ex_03_23;

public class Pair<T> {
    private T part;
    private T counterPart;
    
    public Pair(T part, T counterPart){
        this.part = part;
        this.counterPart = counterPart;
    }
    
    public T getPart(){
        return part;
    }
    
    public T getCounterPart(){
        return counterPart;
    }
}
