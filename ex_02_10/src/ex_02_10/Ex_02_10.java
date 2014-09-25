package ex_02_10;

import java.util.OptionalDouble;
import java.util.stream.Stream;

public class Ex_02_10 {

    public static void main(String[] args) {
        Stream<Double> doubleStream = Stream.of(1.0, 2.0, 3.0);
        OptionalDouble d = reduceAverage(doubleStream);
        d.ifPresent(System.out::println);
        
        doubleStream = Stream.empty();
        d = reduceAverage(doubleStream);
        d.ifPresent(System.out::println);
        
    }
    
    public static OptionalDouble reduceAverage(Stream<Double> doubleStream){
        return doubleStream.mapToDouble(Double::doubleValue).average();
    }
    
    //streamが空きの場合、分母はゼルになっていて、結果はNaNになってしまうから
}
