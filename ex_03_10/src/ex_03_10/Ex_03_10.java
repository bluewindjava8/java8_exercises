package ex_03_10;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Ex_03_10 {


    public static void main(String[] args) {
        //UnaryOperator op = Color::brighter;//型推定できないから
        UnaryOperator<Color> op1 = Color::brighter;
        
        //Function<Color, Color> は UnaryOperator<Color>じゃないから
        //Image finalImage = transform(new Image("abc.jpg"), op1.compose(Color::grayscale));
        //Function<Color, Color> f = op1.compose(Color::grayscale);
        
    }
    
    public static Image transform(Image in, UnaryOperator<Color> f){
        return null;
    }
}
