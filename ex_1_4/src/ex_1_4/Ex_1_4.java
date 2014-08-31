/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ex_1_4;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author bluewind
 */
public class Ex_1_4 {

    /**
     * @param args the command line arguments
     */
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
