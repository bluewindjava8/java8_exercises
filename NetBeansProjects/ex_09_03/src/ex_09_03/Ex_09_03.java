package ex_09_03;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.zip.ZipException;

public class Ex_09_03 {

    public static void main(String[] args)throws FileNotFoundException, MalformedURLException {
        
        try{
            FileInputStream fin = new FileInputStream("input.txt");
            URL url = new URL("http://aa.bb.cc.dd.ee");
        }catch(FileNotFoundException | MalformedURLException ex){
            throw ex;
        }
    }
    
}
