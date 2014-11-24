package ex_04_10;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javax.swing.event.HyperlinkEvent;

public class Ex_04_10 extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        HBox bar = new HBox(20);
        bar.setPadding(new Insets(10));
        Button backButton = new Button("Back");
        TextField address = new TextField();
        address.setPrefWidth(600);
        bar.getChildren().addAll(backButton, address);


        
        String location = "http://www.yahoo.co.jp";
        WebView browser = new WebView();
        WebEngine engine = browser.getEngine();
        engine.getLoadWorker().stateProperty().addListener(
        new ChangeListener<State>() {
            public void changed(ObservableValue ov, State oldState, State newState) {
                if (newState == State.SUCCEEDED) {
                    //primaryStage.setTitle(engine.getLocation());
                    address.setText(engine.getLocation());
                }
            }
        });
        engine.load(location);
        
        backButton.setOnAction(event -> {
            String prevURL = this.goBack(engine);
            address.setText(prevURL);
        });
        
        address.setOnAction(event -> engine.load(address.getText()) );
        
        VBox root = new VBox();
        root.getChildren().addAll(bar, browser);
        
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("My Browser");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private String goBack(WebEngine engine){
        WebHistory history = engine.getHistory();
        ObservableList<WebHistory.Entry> entryList = history.getEntries();
        int currentIndex = history.getCurrentIndex();
        //new Thread(()->history.go(currentIndex > 0 ? -1 : 0)).start();
        history.go(currentIndex > 0 ? -1 : 0);
        return entryList.get(currentIndex>0?currentIndex-1:currentIndex).getUrl();
        
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
