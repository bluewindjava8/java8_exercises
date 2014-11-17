
package ex_04_02;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Greeting {
    private StringProperty textProperty;
    private String text = "";
    
    public final StringProperty textProperty(){
        if(textProperty == null){
            textProperty = new SimpleStringProperty(text);
        }
        
        return textProperty;
    }
    
    public final void setText(String newValue){
        if(textProperty == null){
            text = newValue;
        }else{
            textProperty.set(newValue);
        }
        
    }
    
    public final String getText(){
        return textProperty == null ? text : textProperty.get();
    }
    
    //For test only
    public boolean isTextPropertyNull(){
        return textProperty == null;
    }
    
}
