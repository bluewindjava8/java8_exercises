
package ex_03_13;

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
    
    private static void test(){
        //Stage stage = new Stage();
        Image image = new Image("tom_and_jerry.jpg");
        int width = (int)image.getWidth();
        int height = (int)image.getHeight();
        
        ColorTransformer frameGrayTrans = (x, y, color) -> (x < 10 || x >= width - 10  || y < 10 || y >= height - 10) ? Color.GREY : color;
        Image out1 = LatentImage.from(image).transform(Color::darker, true).transform(frameGrayTrans, true).transform(Color::darker, true).toImage();
        saveToFile(out1, "t1.jpg");
        
        Image out2 = LatentImage.from(image).transform(getBlurTransformer(image), true).toImage();
        saveToFile(out2, "t2.jpg");
        
        Image out3 = LatentImage.from(image).transform(getEdgeTransformer(image), true).toImage();
        saveToFile(out3, "t3.jpg");        
    }
    
    private  static ColorTransformer getBlurTransformer(Image image){
        return (x, y, xyColor) -> getBlurColor(x, y, image);
    }
    
    private static ColorTransformer getEdgeTransformer(Image image){
        return (x, y, xyColor) -> getEdgeColor(x, y, image);
    }
    
    private static Color getBlurColor(int x, int y, Image image){
        Point[] adjacentPoints = getAdjacent8Points(new Point(x, y));
        return caclAverageColor(adjacentPoints, image);
    }
    
    private static Color getEdgeColor(int x, int y, Image image){
        Point centerPoint = new Point(x, y);
        Point[] adjacent4Points = getAdjacent4Points(centerPoint);
        return caclEdgeColor(centerPoint, adjacent4Points, image);
        
        
        
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
    
    private static Color caclAverageColor(Point[] points, Image image){
        double r = 0, g = 0, b = 0;
        for(Point point : points){
            try{
                Color color = image.getPixelReader().getColor(point.x, point.y);
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
    
    private static Color caclEdgeColor(Point centerPoint, Point[] points, Image image){
        double r = 0, g = 0, b = 0;
        for(Point point : points){
            try{
                Color color = image.getPixelReader().getColor(point.x, point.y);
                r += color.getRed();
                g += color.getGreen();
                b += color.getBlue();
            }catch(IndexOutOfBoundsException ex){
                
            }
        }
        Color centerPointColor = image.getPixelReader().getColor(centerPoint.x, centerPoint.y);
        double centerPointRed = centerPointColor.getRed();
        double centerPointGreen = centerPointColor.getGreen();
        double centerPointBlue = centerPointColor.getBlue();
        
        
//        centerPointRed = Math.abs(centerPointRed * 4 - r);
//        centerPointGreen = Math.abs(centerPointGreen * 4 - g);
//        centerPointBlue = Math.abs(centerPointBlue * 4 - b);
        
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

