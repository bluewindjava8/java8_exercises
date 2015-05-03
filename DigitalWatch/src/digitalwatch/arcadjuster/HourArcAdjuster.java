package digitalwatch.arcadjuster;

import java.time.LocalTime;
import javafx.scene.shape.Arc;

public class HourArcAdjuster implements ArcAdjuster {

    @Override
    public void adjustArcByCurrentTime(Arc arc) {
        LocalTime time = LocalTime.now();
        int second = time.getSecond();
        int minute = time.getMinute();
        int hour = time.getHour() % 12;
        double length = -30 * (hour + minute / 60.0 + second / 3600.0);
        arc.setLength(length);
    }

}
