
package ex_03_14;

import java.util.LinkedList;
import java.util.List;
import java.util.function.UnaryOperator;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class LatentImage {
    //private Image in;
    private PixelReader reader;
    private int width, height;
    private boolean terminated;
    
    private List<Transformer> pendingOperations;
    
    private LatentImage(Image image){
        reader = image.getPixelReader();
        width = (int)image.getWidth();
        height = (int)image.getHeight();
        pendingOperations = new LinkedList<>();
        
    }
    
    public static LatentImage from(Image image){
        return new LatentImage(image);
    }
    
    public LatentImage transform(Transformer trans){
        if(terminated){
            throw new IllegalStateException("Already terminated.");
        }
        flush();
        
        pendingOperations.add(trans);
        return this;
    }
    
    public LatentImage transform(UnaryOperator<Color> f){
        if(terminated){
            throw new IllegalStateException("Already terminated.");
        }
        flush();
        
        Transformer trans = (x, y, reader) -> f.apply(reader.getColor(x, y));
        pendingOperations.add(trans);
        return this;
    }
   
    private Image flush(){
        WritableImage out = new WritableImage(width, height);
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                Color c = reader.getColor(x, y);
                for(Transformer trans : this.pendingOperations){
                    c = trans.apply(x, y, reader);
                }
                out.getPixelWriter().setColor(x, y, c);
           }
        }
        
        reader = out.getPixelReader();
        pendingOperations.clear();
        return out;
    }
    
    public Image toImage(){
        Image out = flush();
        terminated = true;
        return out;
    }
    
}
