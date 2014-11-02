
package ex_03_08;

import java.io.File;
import java.security.InvalidParameterException;
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
        int imageWidth = (int)image.getWidth();
        int imageHeight = (int)image.getHeight();
        
        ColorTransformer f1 = createColorTransformer(10, 10, imageWidth, imageHeight, Color.BLUE);
        Image out1 = transform(image, f1);
        saveToFile(out1, "t1.jpg");
        
        ColorTransformer f2 = createColorTransformer(30, 40, imageWidth, imageHeight, Color.BLUE);
        Image out2 = transform(image, f2);
        saveToFile(out2, "t2.jpg");
        
        ColorTransformer f3 = createColorTransformer(imageWidth, 10, imageWidth, imageHeight, Color.BLUE);
        Image out3 = transform(image, f3);
        saveToFile(out3, "t3.jpg");
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
    
    public static ColorTransformer createColorTransformer(int edgeWidth, int edgeHeight, int imageWidth, int imageHeight, 
            Color edgeColor){
        if(edgeWidth * 2 > imageWidth || edgeHeight * 2 > imageHeight){
            throw new InvalidParameterException("edge is too big");
        }
        return (x, y, color) -> (x < edgeWidth || x >= imageWidth - edgeWidth  || y < edgeHeight || y >= imageHeight - edgeHeight) ? edgeColor : color;
        
    }
    
    
}
