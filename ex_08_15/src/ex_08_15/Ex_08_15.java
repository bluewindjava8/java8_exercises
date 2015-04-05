
package ex_08_15;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Ex_08_15 {

    public static void main(String[] args) throws IOException {
        int len = args.length;
        if(len != 2){
            System.out.println("Usage: java Ex_08_15 path pattern");
        }
        find(Paths.get(args[0]), args[1]);
       
    }
    
    public static void find(Path path, String patternStr) throws IOException{
        try(Stream<Path> entries = Files.walk(path)){                    
            //Stream<Path> desiredPaths = entries.peek(entry -> System.out.println("First :" + entry.getFileName())).filter(entry -> entry.toFile().isFile() && isAsciiText(entry)).peek(entry -> System.out.println("Search :" + entry.getFileName())).
            Stream<Path> desiredPaths = entries.filter(entry -> entry.toFile().isFile() && isAsciiText(entry)).
                    filter(entry ->{
                long count = 0;
                boolean matched = false;
                try{
                    //matched = Files.lines(entry).anyMatch(Pattern.compile(patternStr).asPredicate());
                    count = Files.lines(entry).filter(Pattern.compile(patternStr).asPredicate()).count();
                }catch(IOException e){
                    //Do nothing
                    System.out.println("exception.");
                }
                //return matched;
                return count > 0;
                        
            });
            
            desiredPaths.forEach(p -> System.out.println(p.toFile().getPath()));
            //desiredPaths.forEach(p -> System.out.println(p.getFileName()));
                
        }
    }

    public static boolean isAsciiText(Path path){
        try(InputStream in = Files.newInputStream(path);){
            
            byte[] bytes = new byte[500];

            int len = in.read(bytes, 0, bytes.length);
            int x = 0;
            short bin = 0;

            //for (byte thisByte : bytes) {
            for(int i = 0; i < len; i++){
                
                byte thisByte = bytes[i];
                char it = (char) thisByte;
                if (!Character.isWhitespace(it) && Character.isISOControl(it)) {

                    bin++;
                }
                if (bin >= 5) {
                    return false;
                }
                x++;
            }
            return true;
        }catch(IOException e){
            e.printStackTrace();
        }
        return false;
    }

}
