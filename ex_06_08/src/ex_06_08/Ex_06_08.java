
package ex_06_08;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Random;


public class Ex_06_08 {

    public static void main(String[] args) {
        calculateSortSpeed(3000000);     
        calculateSortSpeed(5000000);
        calculateSortSpeed(10000000);
    }
    
    public static void calculateSortSpeed(int size){
        long[] longArrayForSerialSort = new long[size];
        Random random = new Random();
        for(int i = 0; i < size; i++){
            longArrayForSerialSort[i] = random.nextLong();
        }
        
        long[] longArrayForParallelSort = new long[size];
        for(int i = 0; i < size; i++){
            longArrayForParallelSort[i] = longArrayForSerialSort[i];
        }
        
        Instant begin = Instant.now();
        Arrays.parallelSort(longArrayForParallelSort);
        Instant end = Instant.now();
        System.out.println("size : "+ size +" , parallelSort : " + Duration.between(begin, end).toMillis() + "milisconds");
        
        
        begin = Instant.now();
        Arrays.sort(longArrayForSerialSort);
        end = Instant.now();
        System.out.println("size : "+ size +" , sort : " + Duration.between(begin, end).toMillis() + "milisconds");   
        
//        for(int i = 0; i < size; i++){
//            System.out.println(longArrayForParallelSort[i]);
//        }
//        
//        System.out.println();
//        for(int i = 0; i < size; i++){
//            System.out.println(longArrayForSerialSort[i]);
//        }       
    }
}
