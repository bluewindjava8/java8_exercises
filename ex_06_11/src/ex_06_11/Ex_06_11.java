
package ex_06_11;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.io.IOException;
import java.net.PasswordAuthentication;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Ex_06_11 {

    public static void main(String[] args) {
        
        PasswordAuthentication auth = new PasswordAuthentication("", new char[]{'s', 'e', 'c', 'r', 'e', 't'});
        
        Supplier<String> keyboardSupplier =
                () -> {
                        Scanner in = new Scanner(System.in);
                        System.out.print("Please enter your password : ");
                        String username = in.nextLine();       
                        System.out.println("You entered : " + username);
                        return username;
                };
        
        Predicate<String> until = 
                value -> {
                    try{
                        Thread.sleep(1000);
                    }catch(InterruptedException e){
                        throw new RuntimeException(e);
                    }
                    
                    
                    return value.equals(new String(auth.getPassword()));
                };
        
        repeat(keyboardSupplier, until);
                    
    }
    
    public static <T> CompletableFuture<T> repeat(Supplier<T> action, Predicate<T> until){
        T value = action.get();
        if(until.test(value)){
            System.out.println("Completed.");
            return null;
        }else{
            return repeat(action, until);
        }
    }
    
}
