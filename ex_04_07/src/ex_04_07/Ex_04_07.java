
package ex_04_07;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Ex_04_07 extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Label userNameLabel = new Label("User name:");
        Label passwordLabel = new Label("Password:");
        TextField userName = new TextField();
        TextField password = new TextField();
        
        HBox buttons = new HBox(10);
        Button ok = new Button("Ok");
        Button cancel = new Button("Cancel");
        buttons.getChildren().addAll(ok, cancel);
        buttons.setAlignment(Pos.CENTER);
        
        //buttons.setStyle("-fx-border-color: red;");
        //using setBorder method
        buttons.setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, null, null)));
        userNameLabel.setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, null, null)));
        
        GridPane pane = new GridPane();
        pane.setVgap(10);
        pane.setHgap(10);
        pane.setPadding(new Insets(30));
        pane.add(userNameLabel, 0, 0);
        pane.add(userName, 1, 0);
        pane.add(passwordLabel, 0, 1);
        pane.add(password, 1, 1);
        pane.add(buttons, 0, 2, 2, 1);
        GridPane.setHalignment(userNameLabel, HPos.RIGHT);
        GridPane.setHalignment(passwordLabel, HPos.RIGHT);    
        
        pane.setGridLinesVisible(true);
        
        
        Scene scene = new Scene(pane);
        
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
