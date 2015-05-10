package digitalwatch.prefs;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class WatchPreferences {

//    private String fontName;
//    private String fontStyle;
//    private double fontSize;
//
//    private double fontColorR;
//    private double fontColorG;
//    private double fontColorB;
//
//    private double backgroundColorR;
//    private double backgroundColorG;
//    private double backgroundColorB;
//
//    private int winPosX;
//    private int winPosY;
    //private static final String NODE_PATH = WatchPreferences.class.getCanonicalName() + "1";
    private static final String NODE_PATH = "bluewind" + "3";

    Preferences prefsRoot = Preferences.userRoot();
    Preferences prefs = prefsRoot.node(NODE_PATH);

    public void clear(){
        try {
            prefs.clear();
        } catch (BackingStoreException ex) {
            Logger.getLogger(WatchPreferences.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void putFont(Font font) {
        prefs.put("fontName", font.getName());
        prefs.put("fontStyle", font.getStyle());
        prefs.putDouble("fontSize", font.getSize());
        try {
            //prefs.flush();
            prefs.sync();
        } catch (BackingStoreException ex) {
            Logger.getLogger(WatchPreferences.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Font getFont(Font defaultFont) {
        String fontName = prefs.get("fontName", null);
        //String fontStyle = prefs.get("fontStyle", null);
        double fontSize = prefs.getDouble("fontSize", -1);

        if (fontName == null || fontSize == -1) {
            return defaultFont;
        }

        return new Font(fontName, fontSize);

    }

    public void putFontColor(Color color) {
        putColor("fontColor", color);
    }

    public Color getFontColor(Color defaultColor) {
        return getColor("fontColor", defaultColor);
    }

    public void putBackgroundColor(Color color) {
        putColor("backgroundColor", color);
    }

    public Color getBackgroundColor(Color defaultColor) {
        return getColor("backgroundColor", defaultColor);
    }

    private void putColor(String key, Color color) {
        prefs.putDouble(key + "Red", color.getRed());
        prefs.putDouble(key + "Green", color.getGreen());
        prefs.putDouble(key + "Blue", color.getBlue());
        try {
            //prefs.flush();
            prefs.sync();
        } catch (BackingStoreException ex) {
            Logger.getLogger(WatchPreferences.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Color getColor(String key, Color defaultColor) {
        double red = prefs.getDouble(key + "Red", -1);
        double green = prefs.getDouble(key + "Green", -1);
        double blue = prefs.getDouble(key + "Blue", -1);

        if (red < 0 || green < 0 || blue < 0) {
            return defaultColor;
        }

        return Color.color(red, green, blue);
    }
    
    public void putPos(Point point){
        prefs.putDouble("X", point.getX());
          prefs.putDouble("Y", point.getY());      
        try {
            //prefs.flush();
            prefs.sync();
        } catch (BackingStoreException ex) {
            Logger.getLogger(WatchPreferences.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Point getPos(Point defaultPoint){
        double x = prefs.getDouble("X", -1);
        double y = prefs.getDouble("Y", -1);
        
        if(x < 0 || y < 0){
            return defaultPoint;
        }
        
        return new Point(x, y);
    }

    public String getAbsolutePath(){
        return prefs.absolutePath();
    }
}
