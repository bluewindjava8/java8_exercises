/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ex_1_2;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 *
 * @author bluewind
 */
public class Ex_1_2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
//        Stream<String> dirs = Stream.of("/Users/bluewind/", 
//                                        "/Users/bluewind/java8_exercises", 
//                                        "/Users/bluewind/java8_exercises/README.md",
//                                        "/Users/bluewind/java8");
//        try{
//            dirs.forEach(s -> System.out.println(Arrays.asList(getSubdirsByLambda(s))));
//        }catch(Exception e){
//            System.out.println(e);
//        }
        
        String[] dirs = new String[]{"/Users/bluewind/", 
                                        "/Users/bluewind/java8_exercises", 
                                        "/Users/bluewind/java8_exercises/README.md",
                                        "/Users/bluewind/java8"};
        
        for(String dir : dirs){
            try{
                System.out.println(Arrays.asList(getSubdirsByLambda(dir)));
            }catch(RuntimeException e){
                System.out.println(e);
            }
        }
        System.out.println();
        
        for(String dir : dirs){
            try{
                System.out.println(Arrays.asList(getSubdirsByMethodReference(dir)));
            }catch(RuntimeException e){
                System.out.println(e);
            }
        }   
    }
    
    public static File[] getSubdirsByLambda(String pathname) {
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
        
        File[] subdirs = dir.listFiles(file -> file.isDirectory());
        
        return subdirs;
    }
    
        public static File[] getSubdirsByMethodReference(String pathname) {
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
        
        File[] subdirs = dir.listFiles(File::isDirectory);
        
        return subdirs;
    }
    
}
