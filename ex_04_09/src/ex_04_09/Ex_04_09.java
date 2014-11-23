package ex_04_09;

import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.Circle;
import javafx.scene.shape.ClosePath;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Ex_04_09 extends Application {

    @Override
    public void start(Stage primaryStage) {

        final Circle circle = new Circle(20, 20, 20);
        circle.setFill(Color.ORANGE);
        
        Path path = createEllipsePath(600, 400, 200, 100, 0);

        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(4000));
        pathTransition.setPath(path);
        pathTransition.setNode(circle);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(Timeline.INDEFINITE);
        pathTransition.setAutoReverse(false);
        pathTransition.setInterpolator(Interpolator.LINEAR);
        



        Pane root = new Pane();
        root.getChildren().addAll(circle, path);

        Scene scene = new Scene(root, 1000, 1000);

        primaryStage.setTitle("Orbit");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        pathTransition.play();
    }

    private Path createEllipsePath(double centerX, double centerY, double radiusX, double radiusY, double rotate) {
        ArcTo arcTo = new ArcTo();
        arcTo.setX(centerX - radiusX + 1); // to simulate a full 360 degree celcius circle.
        arcTo.setY(centerY - radiusY);
        arcTo.setSweepFlag(false);
        arcTo.setLargeArcFlag(true);
        arcTo.setRadiusX(radiusX);
        arcTo.setRadiusY(radiusY);
        arcTo.setXAxisRotation(rotate);

        Path path = new Path();
        path.getElements().add(new MoveTo(centerX - radiusX, centerY - radiusY));
        path.getElements().add(arcTo);
        path.getElements().add(new ClosePath());
        
                
        path.setStroke(Color.BLUE);
        path.setStrokeWidth(2);
        path.setOpacity(0.8);
        path.getStrokeDashArray().setAll(5d, 5d);
        return path;
    }


    public static void main(String[] args) {
        launch(args);
    }

}
