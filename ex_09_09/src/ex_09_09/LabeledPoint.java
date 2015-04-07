
package ex_09_09;

import java.util.Objects;

public class LabeledPoint {
    private String label;
    private int x;
    private int y;
    
    public LabeledPoint(String label, int x, int y){
        this.label = label;
        this.x = x;
        this.y = y;
    }
    
    @Override
    public boolean equals(Object otherObject){
        if(this == otherObject){
            return true;
        }
        if(otherObject == null){
            return false;
        }
        if(getClass() != otherObject.getClass()){
            return false;
        }
        LabeledPoint other = (LabeledPoint) otherObject;
        
        return Objects.equals(label, other.label) && Objects.equals(x, other.x) && Objects.equals(y, other.y);  
        
    }
    
    @Override
    public int hashCode(){
        return Objects.hash(label, x, y);
    }
}
