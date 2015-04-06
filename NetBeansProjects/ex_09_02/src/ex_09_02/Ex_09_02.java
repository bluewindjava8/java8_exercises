
package ex_09_02;

import java.io.Closeable;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Scanner;


public class Ex_09_02 {

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
        RuntimeException runEx = null;
        
        try{
            in = new Scanner(Paths.get("input.txt"));
            out = new PrintWriter("output2.txt"); 
            
            while (in.hasNext())
                out.println(in.next().toLowerCase());
        }catch(IOException ex){
            System.out.println("Exception happened :" + ex.getMessage());
        }catch(RuntimeException ex){
            runEx = ex;
            throw ex;
        }finally{
            closeIfOpened(in, runEx);
            closeIfOpened(out, runEx);
        }

    }
    
    private static void closeIfOpened(Closeable closeable, RuntimeException runEx){
       try{
           if(closeable == null){
               return;
           }
           closeable.close();
       }catch(IOException ex){
           if(runEx != null){
               runEx.addSuppressed(ex);
           }
       }
    }
}
