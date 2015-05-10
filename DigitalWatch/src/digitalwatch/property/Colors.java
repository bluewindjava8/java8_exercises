
package digitalwatch.property;

import javafx.scene.paint.Color;

public class Colors {
    private Color fontColor;
    private Color backgroundColor;
    
    public Colors(Color fontColor, Color backgroundColor){
        this.fontColor = fontColor;
        this.backgroundColor = backgroundColor;
    }

    public Color getFontColor() {
        return fontColor;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }
    
    @Override
    public String toString(){
        return "yyyyyyyyyyyy font color = " + fontColor + ", background color = " + backgroundColor;
    }
}
