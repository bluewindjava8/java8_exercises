
package ex_03_14;

import java.io.File;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.imageio.ImageIO;


public class DeriveApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        test();
    }
    
    private static void test(){
        //Stage stage = new Stage();
        Image image = new Image("tom_and_jerry.jpg");
        int width = (int)image.getWidth();
        int height = (int)image.getHeight();
        
        Transformer frameGrayTrans = (x, y, reader) -> (x < 10 || x >= width - 10  || y < 10 || y >= height - 10) ? Color.GREY : reader.getColor(x, y);
        Image out1 = LatentImage.from(image).transform(Color::darker)
                .transform(frameGrayTrans)
                .transform(Color::darker)
                .toImage();
        saveToFile(out1, "t1.jpg");
        
        Image out2 = LatentImage.from(image)
                .transform(Color::darker)
                .transform(getBlurTransformer())
                .transform(Color::darker)
                .toImage();
        saveToFile(out2, "t2.jpg");
        
        Image out3 = LatentImage.from(image).transform(Color::brighter)
                .transform(getEdgeTransformer()).toImage();
        saveToFile(out3, "t3.jpg");     
        
        Image out4 = LatentImage.from(image).transform(Color::brighter)
                .transform((x, y, reader) -> reader.getColor(width - 1 - x, y)).toImage();
        saveToFile(out4, "t4.jpg");         
    }
    
    private  static Transformer getBlurTransformer(){
        return (x, y, reader) -> getBlurColor(x, y, reader);
    }
    
    private static Transformer getEdgeTransformer(){
        return (x, y, reader) -> getEdgeColor(x, y, reader);
    }
    
    private static Color getBlurColor(int x, int y, PixelReader reader){
        Point[] adjacentPoints = getAdjacent8Points(new Point(x, y));
        return caclAverageColor(adjacentPoints, reader);
    }
    
    private static Color getEdgeColor(int x, int y, PixelReader reader){
        Point centerPoint = new Point(x, y);
        Point[] adjacent4Points = getAdjacent4Points(centerPoint);
        return caclEdgeColor(centerPoint, adjacent4Points, reader);
        
        
        
    }
    
    private static Point[] getAdjacent8Points(Point p){
        Point[] adjacentPoints = new Point[8];
        adjacentPoints[0] = new Point(p.x -1, p.y -1);
        adjacentPoints[1] = new Point(p.x, p.y -1);
        adjacentPoints[2] = new Point(p.x +1, p.y -1);
        
        adjacentPoints[3] = new Point(p.x -1 , p.y);
        adjacentPoints[4] = new Point(p.x +1, p.y);        
        
        adjacentPoints[5] = new Point(p.x -1, p.y +1);
        adjacentPoints[6] = new Point(p.x, p.y +1);
        adjacentPoints[7] = new Point(p.x +1, p.y +1);   
        
        return adjacentPoints;
    }
    
        private static Point[] getAdjacent4Points(Point p){
        Point[] adjacentPoints = new Point[4];
        adjacentPoints[0] = new Point(p.x, p.y -1);
        
        adjacentPoints[1] = new Point(p.x -1 , p.y);
        adjacentPoints[2] = new Point(p.x +1, p.y);        
        
        adjacentPoints[3] = new Point(p.x, p.y +1);  
        
        return adjacentPoints;
    }
    
    private static Color caclAverageColor(Point[] points, PixelReader reader){
        double r = 0, g = 0, b = 0;
        for(Point point : points){
            try{
                Color color = reader.getColor(point.x, point.y);
                r += color.getRed();
                g += color.getGreen();
                b += color.getBlue();
            }catch(IndexOutOfBoundsException ex){
                
            }
        }
        r /= points.length;
        g /= points.length;
        b /= points.length;
        
        return Color.color(r, g, b);
    }
    
    private static Color caclEdgeColor(Point centerPoint, Point[] points, PixelReader reader){
        double r = 0, g = 0, b = 0;
        for(Point point : points){
            try{
                Color color = reader.getColor(point.x, point.y);
                r += color.getRed();
                g += color.getGreen();
                b += color.getBlue();
            }catch(IndexOutOfBoundsException ex){
                
            }
        }
        Color centerPointColor = reader.getColor(centerPoint.x, centerPoint.y);
        double centerPointRed = centerPointColor.getRed();
        double centerPointGreen = centerPointColor.getGreen();
        double centerPointBlue = centerPointColor.getBlue();
        
        centerPointRed = limitFrom0To1(centerPointRed * 4 - r);
        centerPointGreen = limitFrom0To1(centerPointGreen * 4 - g);
        centerPointBlue = limitFrom0To1(centerPointBlue * 4 - b);        
        
        return Color.color(centerPointRed, centerPointGreen, centerPointBlue);
    }
    
    private static double limitFrom0To1(double color){
        if(color > 1){
            return 1;
        }else if(color < 0){
            return 0;
        }else{
            return color;
        }
    }
    
    private static void saveToFile(Image image, String fileName){
        File file = new File(fileName);

        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
        } catch (Exception s) {
            throw new RuntimeException("Something wrong.");
        }
    }
    
    
    private static class Point{
        private int x;
        private int y;
        
        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}

