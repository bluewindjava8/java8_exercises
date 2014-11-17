
package ex_04_04;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CircleBuilder;
import javafx.stage.Stage;

public class Ex_04_04 extends Application {
    
    @Override
    public void start(Stage primaryStage) {

        
        Circle circle = new Circle(150, Color.RED);

        
        StackPane root = new StackPane();
        root.getChildren().add(circle);
        
        Scene scene = new Scene(root, 300, 250);
        
        circle.centerXProperty().bind(Bindings.divide(scene.widthProperty(), 2));
        circle.centerYProperty().bind(Bindings.divide(scene.heightProperty(), 2));        
        circle.radiusProperty().bind(
                Bindings.divide((scene.widthProperty().get() < scene.heightProperty().get() ? scene.widthProperty() :scene.heightProperty() ), 2));
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
