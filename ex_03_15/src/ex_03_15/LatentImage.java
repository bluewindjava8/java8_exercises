
package ex_03_15;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.UnaryOperator;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class LatentImage {
    private Image in;
    private List<UnaryOperator<Color>> pendingOperations;
    private boolean terminated;
    
    private LatentImage(Image image){
        in = image;
        pendingOperations = new LinkedList<>();
    }
    
    public static LatentImage from(Image image){
        return new LatentImage(image);
    }
    
    public LatentImage transform(UnaryOperator<Color> f){
        if(terminated){
            throw new IllegalStateException("Already terminated.");
        }
        pendingOperations.add(f);
        return this;
    }
    
    
    public Image toImageParallel(){
        int n = Runtime.getRuntime().availableProcessors();
        int width = (int)in.getWidth();
        int height = (int)in.getHeight();
        
        WritableImage out = new WritableImage(width, height);
        
        try{
            
            ExecutorService pool = Executors.newCachedThreadPool();
            
            for(int i = 0; i < n; i++){
                int fromY = i * height / n;
                int toY = (i + 1) * height /n <=  height ? (i + 1) * height /n : height;

                pool.submit(() -> {
                   for(int x = 0; x < width; x++){
                       for(int y = fromY; y < toY; y++){
                           Color color = in.getPixelReader().getColor(x, y);
                           for(UnaryOperator<Color> f : this.pendingOperations){
                               color = f.apply(color);
                           }
                           
                           out.getPixelWriter().setColor(x, y, color);
                       }
                       
                   } 
                });

            }
            pool.shutdown();
            pool.awaitTermination(1, TimeUnit.HOURS);
        }catch(InterruptedException ex){
            ex.printStackTrace();
        }

        terminated = true;
                
        return out;
    }
    
}
