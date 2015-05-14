package digitalwatch;

import digitalwatch.timesource.TimeSource;
import java.time.LocalTime;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;

public class TimeArc extends Arc {

    private boolean dragging = false;
    private Runnable dragEndHandler;

    public TimeArc(int strokeWith, Color strokeColor) {
        setStartAngle(90.0f);
        setLength(0.0f);
        setType(ArcType.OPEN);
        setFill(null);
        setStroke(strokeColor);
        setStrokeWidth(strokeWith);
        setEffect(new DropShadow(20, 0, 0, strokeColor));
        //root.getChildren().add(arc);

        setOnMouseDragged(event -> {
            dragging = true;
            double theta = Math.toDegrees(Math.atan2(getCenterY() - event.getY(), event.getX() - getCenterX()));

            double arcLength = theta - 90;
            arcLength = arcLength < 0 ? arcLength : arcLength - 360;
            setLength(arcLength);
        });

        this.setOnMouseReleased(event -> {
            System.out.println("mouse released.");
            if(dragEndHandler != null){
               dragEndHandler.run();
            }
            dragging = false;
            
        });
    }

    public boolean isDragging() {
        return dragging;
    }
    
    public void setDragEndHanlder(Runnable dragEndHandler){
        this.dragEndHandler = dragEndHandler;
    }
    
    public static LocalTime toTime(double hourDegree, double minuteDegree, double secondDegree, TimeSource timeSource){
        LocalTime time = timeSource.getCurrentTime();
        int hour = time.getHour();
        if(hour < 12)
            return LocalTime.of(((int)hourDegree) / 30 , ((int)minuteDegree) / 6 , ((int)secondDegree) / 6);
        else
            return LocalTime.of(((int)hourDegree) / 30 + 12 , ((int)minuteDegree) / 6 , ((int)secondDegree) / 6);
           
    }
}
