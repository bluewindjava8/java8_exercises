package ex_1_4;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Ex_1_4 {

    public static void main(String[] args) {
                
        File[] dirs = new File[]{new File("/Users/bluewind/"), new File("/Users/bluewind/java8_exercises/ex_1_4")};
        
        for(File dir : dirs){
            try{
                System.out.println(sort(dir.listFiles()));
            }catch(RuntimeException e){
                System.out.println(e);
            }
        }
    }
    
    
    private static List<File> sort(File[] files){
        Map<String, List<File>> groupedFileLists = Stream.of(files).collect(Collectors.groupingBy(f->f.isDirectory() ? "dir" : "file"));        
        
        for(Entry<String, List<File>> entry : groupedFileLists.entrySet()){
            String key = entry.getKey();
            List<File> fileList = entry.getValue();
            
            Collections.sort(fileList, (file1, file2) -> file1.getPath().compareTo(file2.getPath()));
            
        }
        
        List<File> sortedList = groupedFileLists.get("dir");
        sortedList.addAll(groupedFileLists.get("file"));
        
        return sortedList;
        
    }
    
}
