package ex_03_05;

import java.util.function.UnaryOperator;
import javafx.scene.paint.Color;


public class Ex_03_05 {

    public static void main(String[] args) {
        DeriveApplication.launch(DeriveApplication.class, args);
        
    }
    
    
    
}

@FunctionalInterface
interface ColorTransformer {
    Color apply(int x, int y, Color colorAtXY);
}


