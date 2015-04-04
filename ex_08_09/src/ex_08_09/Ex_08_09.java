
package ex_08_09;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Ex_08_09 {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("targetfile.txt");
        Scanner scanner = new Scanner(file);
        scannerToWords(scanner).forEach(System.out::println);
        
        System.out.println("/////////");
        scanner = new Scanner(file);
        scannerToLines(scanner).forEach(System.out::println);
        
        System.out.println("/////////");
        scanner = new Scanner(file);
        scannerToIntegers(scanner).forEach(System.out::println);   
        
        System.out.println("/////////");
        scanner = new Scanner(file);
        scannerToDoubles(scanner).forEach(System.out::println);          
    }
    
    public static Stream<String> scannerToWords(Scanner scanner){
        List<String> words = new ArrayList<>();
        
        while(scanner.hasNext()){
            String word = scanner.next();
            //System.out.println(str);
            words.add(word);

        }
        return words.stream();
    }
    
    public static Stream<String> scannerToLines(Scanner scanner){
        List<String> lines = new ArrayList<>();
        
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            lines.add(line);

        }
        return lines.stream();
    }
    
    public static Stream<Integer> scannerToIntegers(Scanner scanner){
        List<Integer> integers = new ArrayList<>();
        
        while(scanner.hasNext()){
            String token = scanner.next();
            try{
                int intValue = Integer.parseInt(token);
                integers.add(intValue);
            }catch(NumberFormatException e){
                //Do nothing here
            }
        }
        
        return integers.stream();
    }
    
    public static Stream<Double> scannerToDoubles(Scanner scanner){
        List<Double> doubles = new ArrayList<>();
        
        while(scanner.hasNext()){
            boolean doubleParseOk = false;
            boolean intParseOk = false;
            String token = scanner.next();
            
            Double doubleValue = 0.0;

            try{
                doubleValue = Double.parseDouble(token);
                doubleParseOk = true;
            }catch(NumberFormatException e){
                //Do nothing here
            }
            
            if(doubleParseOk){
                try{
                    int intValue = Integer.parseInt(token);
                    intParseOk = true;
                }catch(NumberFormatException e){
                    //Do nothing here
                }
            }
            
            if(doubleParseOk && ! intParseOk){
                doubles.add(doubleValue);
            }
        }

        
        return doubles.stream();
    }
    
}
