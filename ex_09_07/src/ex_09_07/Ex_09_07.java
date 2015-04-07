package ex_09_07;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Ex_09_07 {

    public static void main(String[] args) throws IOException {
       getPage("http://days.nsp.ricoh.co.jp/redmine/");
       
    }
    
    public static void getPage(String address) throws  IOException{
        
        try{
            URL url = new URL(address);
            URLConnection connection = url.openConnection();
            connection.connect();
            InputStream in = connection.getInputStream();

            Files.copy(in, Paths.get("result.html"));

        }catch(MalformedURLException | FileNotFoundException ex){
            throw ex;
        }
        
    }
    
}

