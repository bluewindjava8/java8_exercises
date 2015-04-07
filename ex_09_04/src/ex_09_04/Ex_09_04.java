package ex_09_04;


import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Ex_09_04 {

public static void main(String[] args) throws IOException {
        surfWithAuthorization("http://stackoverflow.com/users/4600627/bluewind",
                "xxxxxxe@hotmail.com", "XXXXX###");
        
    }
    
    public static void surfWithAuthorization(String address, String userName, String password) throws  IOException{
        
        try{
            String plainNameAndPassword = userName + ":" + password;
            Base64.Encoder encoder = Base64.getEncoder();
            String encodedNameAndPassword = encoder.encodeToString(plainNameAndPassword.getBytes(StandardCharsets.UTF_8));

            URL url = new URL(address);//MalformedURLException
            URLConnection connection = url.openConnection();
            connection.setRequestProperty("Authorization", "Basic " + encodedNameAndPassword);
            connection.connect();
            InputStream in = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("result.txt")));//FileNotFoundException


            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                    //System.out.println(sCurrentLine);
                writer.write(currentLine);
                writer.newLine();
            }
        }catch(MalformedURLException | FileNotFoundException ex){
            throw ex;
        }
        
    } 
}
