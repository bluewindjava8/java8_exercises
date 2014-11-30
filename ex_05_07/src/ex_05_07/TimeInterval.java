
package ex_05_07;

import java.security.InvalidParameterException;
import java.time.LocalTime;

public class TimeInterval {
    private LocalTime start;
    private LocalTime end;
    
    private TimeInterval(LocalTime start, LocalTime end){
        if(start.equals(end) || start.isAfter(end)){
            throw new InvalidParameterException("Start should be earlier than end.");
        }
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
    
    public boolean isOverlapping(TimeInterval interval){
        TimeInterval earlierStartedInterval = !this.start.isAfter(interval.start) ? this : interval;
        TimeInterval laterStartedInterval = earlierStartedInterval == this ? interval : this;
        
        return laterStartedInterval.start.isBefore(earlierStartedInterval.end);
    }
}
