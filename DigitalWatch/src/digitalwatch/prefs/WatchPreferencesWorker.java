
package digitalwatch.prefs;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class WatchPreferencesWorker {

    private WatchPreferences prefs = new WatchPreferences();
    
    public void saveWatchPreference(Font font, Color fontColor, Color backgroundColor, Point leftUpCorner){
        prefs.putFont(font);
        prefs.putFontColor(fontColor);
        prefs.putBackgroundColor(backgroundColor);
        prefs.putPos(leftUpCorner);
    }
    
}
