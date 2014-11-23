
package ex_04_06;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Ex_04_06 extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Button top = new Button("top");
        Button left = new Button("left");
        Button center = new Button("center");
        Button right = new Button("right");
        Button bottom = new Button("bottom");
        
        HBox topBox = new HBox();
        topBox.setAlignment(Pos.CENTER);
        topBox.getChildren().add(top);
        
        HBox bottomBox = new HBox();
        bottomBox.setAlignment(Pos.CENTER);
        bottomBox.getChildren().add(bottom);
        
        BorderPane root = new BorderPane();
        root.setTop(topBox);
        root.setLeft(left);
        root.setCenter(center);
        root.setRight(right);
        root.setBottom(bottomBox);
        
        
        Scene scene = new Scene(root);
        
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
