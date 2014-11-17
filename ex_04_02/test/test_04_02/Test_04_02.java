package test_04_02;


import ex_04_02.Greeting;
import javafx.beans.property.StringProperty;
import junit.framework.Assert;
import org.junit.Test;

public class Test_04_02 {
    @Test
    public void testSetAndGetOnly(){
        Greeting greet = new Greeting();
        
        greet.setText("abc");
        Assert.assertEquals("abc", greet.getText());
        Assert.assertEquals(true, greet.isTextPropertyNull());
    }
    
    @Test
    public void testTextProperty(){
        Greeting greet = new Greeting();
        
        greet.setText("abc");
        Assert.assertEquals("abc", greet.getText());
        Assert.assertEquals(true, greet.isTextPropertyNull());
        
        StringProperty property = greet.textProperty();
        Assert.assertEquals("abc", property.get());
        Assert.assertEquals(false, greet.isTextPropertyNull());
        
        property.set("efgh");
        Assert.assertEquals("efgh", property.get());
        Assert.assertEquals("efgh", greet.getText());        
        Assert.assertEquals(false, greet.isTextPropertyNull());
        
    }
    

}
