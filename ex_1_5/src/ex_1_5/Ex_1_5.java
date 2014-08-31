/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ex_1_5;

/**
 *
 * @author bluewind
 */
public class Ex_1_5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
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
