
package digitalwatch.timesource;

import java.time.LocalTime;

public class RealTimeSource implements TimeSource{

    @Override
    public LocalTime getCurrentTime() {
        return LocalTime.now();
    }

    @Override
    public void setCurrentTime(LocalTime currentTime) {
        throw new UnsupportedOperationException("setCurrentTime of RealTimeSource is not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
