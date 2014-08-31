/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ex_1_6;

/**
 *
 * @author bluewind
 */
public class Ex_1_6 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new Thread(uncheck(()->{System.out.println("Zzz"); Thread.sleep(1000);})).start();
//        new Thread(uncheck( new RunnableEx(){
//
//            @Override
//            public void runEx()throws Exception {
//                System.out.println("Zzz"); Thread.sleep(1000);
//            }
//            
//        })).start();
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


