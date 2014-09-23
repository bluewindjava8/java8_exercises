package ex_1_5;

public class Ex_1_5 {

    public static void main(String[] args) {
        Ex_1_5.startThreadNormally();
        Ex_1_5.startThreadWithLambda();
    }
    
    public static void startThreadNormally(){
        Runnable r = new Runnable(){
            public void run(){
                System.out.println("Hello");
            }
        };
        
        new Thread(r).start();
    }
    
    public static void startThreadWithLambda(){
        Runnable r = ()->System.out.println("world");
        
        new Thread(r).start();
    } 
}
