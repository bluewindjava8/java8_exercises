
package ex_08_02;

public class Ex_08_02 {

    public static void main(String[] args) {
        try{
            Math.negateExact(Integer.MIN_VALUE);
        }catch(Exception e){
            System.out.println(e);
        }
        
        try{
            Math.negateExact(Long.MIN_VALUE);
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
}
