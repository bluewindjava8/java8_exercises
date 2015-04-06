
package ex_04_01;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Ex_04_01 extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        String info = "Hello, Fx";
        Label message = new Label(info);
        message.setFont(new Font(100));
        
        TextField text = new TextField(info);
        
        message.textProperty().bind(text.textProperty());
        
        VBox container = new VBox();
        container.getChildren().addAll(message, text);
       
        Scene scene = new Scene(container);
        
        primaryStage.setTitle("Hello, Fx!");
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
