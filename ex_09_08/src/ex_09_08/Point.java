package ex_09_08;

public class Point implements Comparable<Point>{
    private int x;
    private int y;
    
    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    @Override
    public int compareTo(Point other){
        if(x > other.x){
            return 1;
        }else if(x < other.x){
            return -1;
        }else{
            if(y > other.y){
                return 1;
            }else if(y < other.y){
                return -1;
            }else{
                return 0;
            }
        }
    }
    
    public int compareToOverflow(Point other){
        int diff = x -other.x;
        if(diff != 0) return diff;
        return y - other.y;
    }

}
