
package ex_03_12;

import java.util.LinkedList;
import java.util.List;
import java.util.function.UnaryOperator;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class LatentImage {
    private Image in;
    private List<ColorTransformer> pendingOperations;
    
    private LatentImage(Image image){
        in = image;
        pendingOperations = new LinkedList<>();
    }
    
    public static LatentImage from(Image image){
        return new LatentImage(image);
    }
    
    public LatentImage transform(ColorTransformer trans){
        pendingOperations.add(trans);
        return this;
    }
    
    public LatentImage transform(UnaryOperator<Color> f){
        ColorTransformer trans = (x, y, xyColor) -> f.apply(xyColor);
        pendingOperations.add(trans);
        return this;
    }
    
    public Image toImage(){
        int width = (int)in.getWidth();
        int height = (int)in.getHeight();
        System.out.println(width + ", " + height);
        WritableImage out = new WritableImage(width, height);
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                Color c = in.getPixelReader().getColor(x, y);
                for(ColorTransformer trans : this.pendingOperations){
                    c = trans.apply(x, y, c);
                }
                    
                out.getPixelWriter().setColor(x, y, c);

            }
        }
                
        return out;
    }
    
}
