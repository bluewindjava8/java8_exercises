
package ex_03_11;

import java.util.function.UnaryOperator;
import javafx.scene.paint.Color;

public class Ex_03_11 {

    public static void main(String[] args) {
        // TODO code application logic here
        DeriveApplication.launch(DeriveApplication.class);
    }
    
        
    public static ColorTransformer compose(ColorTransformer trans1, ColorTransformer trans2){
        return (x, y, xyColor) -> trans2.apply(x, y, trans1.apply(x, y, xyColor));
    }
    
    public static ColorTransformer convert(UnaryOperator<Color> f){
        return (x, y, xyColor) -> f.apply(xyColor);
    }
    
}

@FunctionalInterface
interface ColorTransformer {
    Color apply(int x, int y, Color colorAtXY);
}