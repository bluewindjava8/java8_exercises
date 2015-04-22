/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package watchtest;

import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author bluewind
 */
public class WatchTest extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        //StackPane root = new StackPane();
        Group root = new Group();
        //root.getChildren().add(btn);
        
        Circle circle = new Circle(100, 100, 100);
        //root.getChildren().add(circle);
        circle.fillProperty().setValue(Color.BLUE);
        
        Scene scene = new Scene(root, 400, 300);
                
        Arc arc = new Arc();
        arc.setCenterX(scene.getWidth() / 2);
        arc.setCenterY(scene.getHeight() / 2);
        arc.setRadiusX(100.0f);
        arc.setRadiusY(100.0f);
        arc.setStartAngle(90.0f);
        arc.setLength(0.0f);
        arc.setType(ArcType.OPEN);
        arc.setFill(null);
        arc.setStroke(Color.BLACK);
        arc.setStrokeWidth(15);
        root.getChildren().add(arc);
        
        Arc arc2 = new Arc();
        arc2.setCenterX(scene.getWidth() / 2);
        arc2.setCenterY(scene.getHeight() / 2);
        arc2.setRadiusX(50.0f);
        arc2.setRadiusY(50.0f);
        arc2.setStartAngle(90.0f);
        arc2.setLength(0.0f);
        arc2.setType(ArcType.OPEN);
        arc2.setFill(null);
        arc2.setStroke(Color.BLUE);
        arc2.setStrokeWidth(10);
        root.getChildren().add(arc2);
        

        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
       
        
        //Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), ev -> arc.setLength(arc.getLength() + 10)));
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), ev -> {drawArcsUsingCurrentTime(arc);drawArcsUsingCurrentTime(arc2);}));
        
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    
    private void drawArcsUsingCurrentTime(Arc secondArc){
        LocalTime time = LocalTime.now();
        int second = time.getSecond();
        double length = - 6.0 * second;
//        if(length < -180.0){
//            length += 360.0;
//        }
        secondArc.setLength(length);
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
