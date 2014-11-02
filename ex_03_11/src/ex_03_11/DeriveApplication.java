
package ex_03_11;

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
        System.out.println("hello");
        test();
    }
    
    public static void test(){
        //Stage stage = new Stage();
        Image image = new Image("tom_and_jerry.jpg");
        int width = (int)image.getWidth();
        int height = (int)image.getHeight();
        ColorTransformer brighterTrans = Ex_03_11.convert(Color::brighter);
        ColorTransformer frameGrayTrans = (x, y, color) -> (x < 10 || x >= width - 10  || y < 10 || y >= height - 10) ? Color.GREY : color;
        ColorTransformer composedTrans = Ex_03_11.compose(brighterTrans, frameGrayTrans);
        
        Image out = transform(image, composedTrans);
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
