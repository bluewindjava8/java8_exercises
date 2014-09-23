package ex_1_6;

public class Ex_1_6 {

    public static void main(String[] args) {
        new Thread(uncheck(()->{System.out.println("Zzz"); Thread.sleep(1000);})).start();
    }
    
    public static Runnable uncheck(RunnableEx runner) {
        return () -> {
                        try{
                            runner.runEx();
                        }catch(Exception e){
                            throw new RuntimeException("uncheck exception.");
                        }
                    };
    }
}

interface RunnableEx {
    void runEx()throws Exception;
}


