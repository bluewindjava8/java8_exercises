package ex_1_3;

import java.io.File;
import java.util.Arrays;

public class Ex_1_3 {

    public static void main(String[] args) {
        String[] dirs = new String[]{"/Users/bluewind/java8_exercises/ex_1_3", "/Users/bluewind/java8_exercises/ex_1_2"};
        
        for(String dir : dirs){
            try{
                System.out.println(Arrays.asList(getFilesWithExtension(dir, "xml")));
            }catch(RuntimeException e){
                System.out.println(e);
            }
        }
    }
    
    public static String[] getFilesWithExtension(String pathname, String extension){
        if(pathname == null) {
            throw new NullPointerException("pathname is null.");
        }
        
        File dir = new File(pathname);
        if(!dir.exists()){
            throw new RuntimeException(pathname + " not exist.");
        }
        
        if(!dir.isDirectory()){
            throw new RuntimeException(pathname + " is not a dir.");
        }
       
        //エンクロージングスコープからキャプチャされる変数はextensionです
        String[] filenames = dir.list((directory, filename) -> filename.endsWith("." + extension) && new File(directory, filename).isFile());
        return filenames;
    }
}
