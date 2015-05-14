
package digitalwatch.timesource;

import java.time.LocalTime;

public interface TimeSource {
    public LocalTime getCurrentTime();
    public void setCurrentTime(LocalTime currentTime);
}
