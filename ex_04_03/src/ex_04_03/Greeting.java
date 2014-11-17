
package ex_04_03;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Greeting {
    
    public final static String DEFAULT_GREETING_VALUE="hello";
    
    private StringProperty textProperty;
    private String text = DEFAULT_GREETING_VALUE;
    
    public final StringProperty textProperty(){
        if(textProperty == null){
            textProperty = new SimpleStringProperty(text);
        }
        
        return textProperty;
    }
    
    public final void setText(String newValue){
        if(textProperty == null){
            if(!newValue.equals(text)){ 
                textProperty = new SimpleStringProperty(newValue);
            }
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
