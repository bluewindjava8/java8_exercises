
package ex_03_15;

import java.io.File;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.imageio.ImageIO;


public class DeriveApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        test();
    }
    
    public static void test(){
        Image image = new Image("tom_and_jerry.jpg");
        
        Image out = LatentImage.from(image).transform(Color::darker)
                .transform(Color::darker).toImageParallel();

        saveToFile(out, "t.jpg");
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
