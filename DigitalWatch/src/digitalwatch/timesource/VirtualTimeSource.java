
package digitalwatch.timesource;

import java.time.Duration;
import java.time.LocalTime;

public class VirtualTimeSource implements TimeSource{

    private Duration diff = Duration.ofSeconds(0);
    
//    public VirtualTimeSource(Duration diff){
//        this.diff = diff;
//    }
    
    @Override
    public LocalTime getCurrentTime() {
        return LocalTime.now().plus(diff);
    }

    @Override
    public void setCurrentTime(LocalTime currentTime) {
        diff = Duration.between(LocalTime.now(), currentTime );
    }
    
}
