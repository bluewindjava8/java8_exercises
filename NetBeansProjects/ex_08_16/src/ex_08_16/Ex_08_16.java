
package ex_08_16;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Ex_08_16 {


    public static void main(String[] args) throws IOException {
        
        Pattern pattern = Pattern.compile("(?<city>[\\p{L} ]+),\\s*(?<state>[A-Z]{2}),\\s*(?<zip>(\\d{5}|\\d{9}))$");
        List<String> lines = Files.readAllLines(Paths.get("data.txt"));
        for(String line : lines){
            Matcher matcher = pattern.matcher(line);
            if(matcher.find()){  
                System.out.println(matcher.group("city")+","+matcher.group("state")+","+matcher.group("zip"));  
            } 
        }

    }
    
}
