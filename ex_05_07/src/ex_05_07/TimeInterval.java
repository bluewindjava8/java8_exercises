
package ex_05_07;

import java.time.LocalTime;

public class TimeInterval {
    private LocalTime start;
    private LocalTime end;
    
    private TimeInterval(LocalTime start, LocalTime end){
        this.start = start;
        this.end = end;
    }
    
    public static TimeInterval of(LocalTime start, LocalTime end){
        return new TimeInterval(start, end);
    }
    
    public boolean isInside(LocalTime time){
        return !start.isAfter(time) && !time.isAfter(end);
    }
    
    public LocalTime getStart(){
        return start;
    }
    
    public LocalTime getEnd(){
        return end;
    }
    
    public boolean equals(TimeInterval interval){
        return start.equals(interval.start) && end.equals(interval.end);
    }
    
}
