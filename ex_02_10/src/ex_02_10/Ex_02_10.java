package ex_02_10;

import java.util.Optional;
import java.util.OptionalDouble;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class Ex_02_10 {

    public static void main(String[] args) {
        Stream<Double> doubleStream = Stream.of(1.0, 2.0, 3.0);
        OptionalDouble d = reduceAverage(doubleStream);
        d.ifPresent(System.out::println);
        
        doubleStream = Stream.empty();
        d = reduceAverage(doubleStream);
        d.ifPresent(System.out::println);
        
        doubleStream = Stream.of(1.0, 2.0, 3.0);
        reduceAvg(doubleStream).ifPresent(System.out::println);
        doubleStream = Stream.empty();
        reduceAvg(doubleStream).ifPresent(System.out::println);
        
        
    }
    
    public static OptionalDouble reduceAverage(Stream<Double> doubleStream){
        return doubleStream.mapToDouble(Double::doubleValue).average();
    }
    
    public static Optional<Double> reduceAvg(Stream<Double> doubleStream){
        AtomicInteger i = new AtomicInteger(0);
        return doubleStream.reduce((x, y) -> {i.incrementAndGet(); return (x * i.intValue() + y) / (i.intValue()+1);});
    }
    
    //streamが空きの場合、分母はゼルになっていて、結果はNaNになってしまうから
}
