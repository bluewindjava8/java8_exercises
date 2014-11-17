package test_04_03;


import ex_04_03.Greeting;
import java.util.Arrays;
import java.util.Comparator;
import javafx.beans.property.StringProperty;
import junit.framework.Assert;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class Test_04_03 {
    @Test
    public void testSetDefaultValue(){
        Greeting greet = new Greeting();
        
        greet.setText(Greeting.DEFAULT_GREETING_VALUE);
        Assert.assertEquals(Greeting.DEFAULT_GREETING_VALUE, greet.getText());
        Assert.assertEquals(true, greet.isTextPropertyNull());
    }
    
    
    @Test
    public void testSetNonDefaultValue(){
        Greeting greet = new Greeting();
        greet.setText("abc");
        Assert.assertEquals("abc", greet.getText());
        Assert.assertEquals(false, greet.isTextPropertyNull()); 
    }
    
    
    @Test
    public void testTextProperty(){
        Greeting greet = new Greeting();
        
        greet.setText("abc");
        Assert.assertEquals("abc", greet.getText());
        Assert.assertEquals(false, greet.isTextPropertyNull());
        
        StringProperty property = greet.textProperty();
        Assert.assertEquals("abc", property.get());
        Assert.assertEquals(false, greet.isTextPropertyNull());
        
        property.set("efgh");
        Assert.assertEquals("efgh", property.get());
        Assert.assertEquals("efgh", greet.getText());        
        Assert.assertEquals(false, greet.isTextPropertyNull());
        
    }
    

}
