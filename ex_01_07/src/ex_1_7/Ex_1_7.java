

package ex_1_7;

public class Ex_1_7 {

    public static void main(String[] args) {
        Runnable r = andThen(()->System.out.println("First run."), 
                             ()->System.out.println("Second run."));
        
        r.run();
    }
    
    public static Runnable andThen(Runnable first, Runnable second) {
        return ()->{first.run();second.run();};
    }
    
}
