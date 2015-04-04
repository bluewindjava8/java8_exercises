
package ex_08_06;

//import java.awt.geom.Point2D;
//import java.awt.geom.Rectangle2D;
import java.util.Arrays;
import java.util.Comparator;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;


public class Ex_08_06 {

    public static void main(String[] args) {
        Comparator<Point2D> pointComparator = Comparator.comparing(Point2D::getX).thenComparing(Point2D::getY);
        Comparator<Rectangle2D> rectangleComparator = Comparator.comparing(Rectangle2D::getMinX)
                .thenComparing(Rectangle2D::getMinY).thenComparing(Rectangle2D::getMaxX).thenComparing(Rectangle2D::getMaxY);
        
        Point2D[] points = new Point2D[]{new Point2D(0, 0), new Point2D(1, 10), new Point2D(1, 5), 
                                         new Point2D(2, 10), new Point2D(2, 2), new Point2D(2, 3),
                                         new Point2D(5, 0), new Point2D(4, 100)};
        
        Rectangle2D[] rects = new Rectangle2D[]{ new Rectangle2D(0, 0, 10, 10), new Rectangle2D(0, 0, 9, 100),
                                                 new Rectangle2D(1, 2, 1, 1), new Rectangle2D(3, 10, 3, 9),
                                                 new Rectangle2D(2, 0, 199, 3), new Rectangle2D(1, 100, 9, 8)};
        
        
        System.out.println("Before sort.");
        System.out.println(Arrays.asList(points));
        Arrays.sort(points, pointComparator);
        System.out.println("After sort.");
        System.out.println(Arrays.asList(points));
        
        System.out.println("Before sort.");
        System.out.println(Arrays.asList(rects));
        Arrays.sort(rects, rectangleComparator);
        System.out.println("After sort.");
        System.out.println(Arrays.asList(rects));  
        
        
    }
    
}
