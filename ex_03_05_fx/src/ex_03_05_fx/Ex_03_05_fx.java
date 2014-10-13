

package ex_03_05_fx;

import java.io.File;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.imageio.ImageIO;


public class Ex_03_05_fx extends Application {
    
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
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        test();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    public static void test(){
        //Stage stage = new Stage();
        Image image = new Image("tom_and_jerry.jpg");
        int width = (int)image.getWidth();
        int height = (int)image.getHeight();
        ColorTransformer f = (x, y, color) -> (x < 10 || x >= width - 10  || y < 10 || y >= height - 10) ? Color.GREY : color;
        
        Image out = transform(image, f);
        saveToFile(out, "t.jpg");
    }
    
    public static Image transform(Image in, ColorTransformer f){
        int width = (int)in.getWidth();
        int height = (int)in.getHeight();
        System.out.println(width + ", " + height);
        WritableImage out = new WritableImage(width, height);
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                out.getPixelWriter().setColor(x, y, f.apply(x, y, in.getPixelReader().getColor(x, y)));

            }
            //System.out.println("x = " + x);
        }
                
        return out;
    }
    
    public static void saveToFile(Image image, String fileName){
        File file = new File(fileName);

        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
        } catch (Exception s) {
            throw new RuntimeException("Something wrong.");
        }
    }
    
}

@FunctionalInterface
interface ColorTransformer {
    Color apply(int x, int y, Color colorAtXY);
}
