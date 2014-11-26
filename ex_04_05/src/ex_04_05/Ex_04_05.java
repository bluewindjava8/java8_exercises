
package ex_04_05;

import java.util.function.Function;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Ex_04_05 extends Application {
    
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
        
        Slider slider = new Slider(0, 200, 50);
        slider.prefWidth(120);
        slider.setShowTickLabels(true);
        
        Slider mirroSlider = new Slider(0, 400, 100);
        mirroSlider.setShowTickLabels(true);
        mirroSlider.prefWidth(slider.getPrefWidth() * 2);
        mirroSlider.valueProperty().bind(observe(t -> 2 * t.doubleValue(), slider.valueProperty()));
        
        btn.disableProperty().bind(observe(t -> t.doubleValue() >= 100, slider.valueProperty()));
        
        VBox root = new VBox();
        root.getChildren().addAll(btn, slider, mirroSlider);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static <T, R> ObservableValue<R> observe(Function<T, R> f, ObservableValue<T> t){
        return new ObservableValue<R>(){

            @Override
            public void addListener(ChangeListener<? super R> listener) {
                t.addListener((observable, oldValue, newValue) -> listener.changed(null, f.apply(oldValue), f.apply(newValue)));

            }
            
            
            @Override
            public void removeListener(ChangeListener<? super R> listener) {
                
            }

            @Override
            public R getValue() {
                T srcValue = t.getValue();
                R destValue = f.apply(srcValue);
                return destValue;
            }

            @Override
            public void addListener(InvalidationListener listener) {
                t.addListener((observable)-> listener.invalidated(observable));
            }

            @Override
            public void removeListener(InvalidationListener listener) {
 
            }
            
        };
        
        
    }

}
