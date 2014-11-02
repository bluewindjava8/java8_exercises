
package ex_03_14;

import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;

public class Ex_03_14 {

    public static void main(String[] args) {
        DeriveApplication.launch(DeriveApplication.class);
    }
    
}

@FunctionalInterface
interface Transformer {
    Color apply(int x, int y, PixelReader reader);
}