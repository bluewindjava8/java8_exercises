
package ex_03_06;

import java.io.File;
import java.util.function.BiFunction;
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
        System.out.println("hello");
        test();
    }
    
    public static void test(){
        //Stage stage = new Stage();
        Image image = new Image("tom_and_jerry.jpg");
        int width = (int)image.getWidth();
        int height = (int)image.getHeight();
        BiFunction<Color, Double, Color> f = (c, factor) -> c.deriveColor(0, 1, factor, 1);
        
        Image out = transform(image, f, 3.0);
        saveToFile(out, "t.jpg");
    }
    
    public static <T> Image transform(Image in, BiFunction<Color, T, Color> f, T arg){
        int width = (int)in.getWidth();
        int height = (int)in.getHeight();
        System.out.println(width + ", " + height);
        WritableImage out = new WritableImage(width, height);
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                out.getPixelWriter().setColor(x, y, f.apply(in.getPixelReader().getColor(x, y), arg));

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
