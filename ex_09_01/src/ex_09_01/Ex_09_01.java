
package ex_09_01;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Scanner;

public class Ex_09_01 {

    public static void main(String[] args) throws IOException {
        test1();
        test2();
    }
    
    public static void test1() throws IOException{
        try (Scanner in = new Scanner(Paths.get("input.txt"));
            PrintWriter out = new PrintWriter("output1.txt")) {
            
            while (in.hasNext())
                out.println(in.next().toLowerCase());
        }
    }

    public static void test2(){
        Scanner in = null;
        PrintWriter out = null;
        
        try{
            in = new Scanner(Paths.get("input.txt"));
            out = new PrintWriter("output2.txt"); 
            
            while (in.hasNext())
                out.println(in.next().toLowerCase());
        }catch(IOException ex){
            System.out.println("Exception happened :" + ex.getMessage());
        }catch(RuntimeException ex){
            throw ex;
        }finally{
            closeIfOpened(in);
            closeIfOpened(out);
        }

    }
    
    private static boolean closeIfOpened(Closeable closeable){
       try{
           if(closeable == null){
               return true;
           }
           closeable.close();
           return true;
       }catch(IOException ex){
           return false;
       }
    }
}
