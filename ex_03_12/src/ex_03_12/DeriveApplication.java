
package ex_03_12;

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
        //Stage stage = new Stage();
        Image image = new Image("tom_and_jerry.jpg");
        int width = (int)image.getWidth();
        int height = (int)image.getHeight();
        ColorTransformer frameGrayTrans = (x, y, color) -> (x < 10 || x >= width - 10  || y < 10 || y >= height - 10) ? Color.GREY : color;
        
        Image out = LatentImage.from(image).transform(Color::darker).transform(frameGrayTrans).transform(Color::darker).toImage();

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
