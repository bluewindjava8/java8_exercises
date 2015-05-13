
package digitalwatch.arcadjuster;

import java.time.LocalTime;
import javafx.scene.shape.Arc;

public class SecondArcAdjuster implements ArcAdjuster{

    @Override
    public void adjustArcByCurrentTime(Arc arc) {
        LocalTime time = LocalTime.now();
        int milliSecond = time.getNano() / 1000000;
        int second = time.getSecond();
        //double length = -6.0 * (second + milliSecond / 1000.0);
        double length = -6.0 * (second);        
        arc.setLength(length);
    }
    
}
