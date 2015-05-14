package digitalwatch.arcadjuster;

import digitalwatch.timesource.TimeSource;
import java.time.LocalTime;
import javafx.scene.shape.Arc;

public class MinuteArcAdjuster implements ArcAdjuster {

    private final TimeSource timeSource;

    public MinuteArcAdjuster(TimeSource timeSource) {
        this.timeSource = timeSource;
    }

    @Override
    public void adjustArcByCurrentTime(Arc arc) {
        LocalTime time = timeSource.getCurrentTime();
        int second = time.getSecond();
        int minute = time.getMinute();
        double length = -6.0 * (minute + second / 60.0);
        arc.setLength(length);
    }

}
