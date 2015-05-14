package digitalwatch.arcadjuster;

import digitalwatch.timesource.TimeSource;
import java.time.LocalTime;
import javafx.scene.shape.Arc;

public class HourArcAdjuster implements ArcAdjuster {

    private final TimeSource timeSource;

    public HourArcAdjuster(TimeSource timeSource) {
        this.timeSource = timeSource;
    }

    @Override
    public void adjustArcByCurrentTime(Arc arc) {
        LocalTime time = timeSource.getCurrentTime();
        int second = time.getSecond();
        int minute = time.getMinute();
        int hour = time.getHour() % 12;
        double length = -30 * (hour + minute / 60.0 + second / 3600.0);
        arc.setLength(length);
    }

}
