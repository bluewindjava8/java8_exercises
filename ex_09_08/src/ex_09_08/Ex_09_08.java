package ex_09_08;

public class Ex_09_08 {

    public static void main(String[] args) {
        Point p1 = new Point(Integer.MAX_VALUE, 10);
        Point p2 = new Point(-10, 11);
        System.out.println(p1.compareTo(p2));
        System.out.println(p1.compareToOverflow(p2));
        
        Point p3 = new Point(Integer.MAX_VALUE, 10);
        Point p4 = new Point(Integer.MAX_VALUE, 15);
        System.out.println(p3.compareTo(p4));
        System.out.println(p3.compareToOverflow(p4));
        
    }
    
}

