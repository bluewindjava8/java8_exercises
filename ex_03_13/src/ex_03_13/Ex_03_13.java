package ex_03_13;

import javafx.scene.paint.Color;

public class Ex_03_13 {

    public static void main(String[] args) {
        DeriveApplication.launch(DeriveApplication.class);
    }
    
}

@FunctionalInterface
interface ColorTransformer {
    Color apply(int x, int y, Color colorAtXY);
}