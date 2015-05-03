package digitalwatch.arcadjuster;

import java.time.LocalTime;
import javafx.scene.shape.Arc;

public class MinuteArcAdjuster implements ArcAdjuster {

    @Override
    public void adjustArcByCurrentTime(Arc arc) {
        LocalTime time = LocalTime.now();
        int second = time.getSecond();
        int minute = time.getMinute();
        double length = -6.0 * (minute + second / 60.0);
        arc.setLength(length);
    }

}
