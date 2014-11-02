package ex_03_08;

import javafx.scene.paint.Color;

public class Ex_03_08 {

    public static void main(String[] args) {
        DeriveApplication.launch(DeriveApplication.class, args);
        
    }    
}


@FunctionalInterface
interface ColorTransformer {
    Color apply(int x, int y, Color colorAtXY);
}