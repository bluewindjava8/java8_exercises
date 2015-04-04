
package ex_08_10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Ex_08_10 {

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("/", "Users", "bluewind", "java8_exercises", "ex_08_03");
        showDir(path, "static", "Test");
    }
    
    public static void showDir(Path path, String keyword1, String keyword2) throws IOException{
        try(Stream<Path> entries = Files.walk(path)){
            //entries.forEach(entry -> System.out.println(entry.getFileName()));
            //long count = entries.filter(entry -> entry.toFile().isFile() ).count();
            //System.out.println(count);
            
            //entries.filter(entry -> entry.toFile().isFile() && entry.toString().endsWith(".java") ).forEach(System.out::println);
                    
            Stream<Path> desiredPaths = entries.filter(entry -> entry.toFile().isFile() && entry.toString().endsWith(".java")).
                                        filter(entry ->{
                long count = 0;
                try{
                    count = Files.lines(entry).
                            filter(line -> line.contains(keyword1) || line.contains(keyword2)).count();
                }catch(IOException e){
                    //Do nothing
                    System.out.println("exception.");
                }
                return count > 0;
                        
            });
            
            desiredPaths.forEach(p -> System.out.println(p.getFileName()));
                
        }
    }
}
