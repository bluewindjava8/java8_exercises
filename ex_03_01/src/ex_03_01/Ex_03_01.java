
package ex_03_01;

import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Ex_03_01 {

    public static void main(String[] args) {
        test();
    }

    public static void test(){
        Logger parentLogger = Logger.getLogger("sampleLogging");
                
        ConditionalLogger logger = new ConditionalLogger("test", null);
        logger.setParent(parentLogger); 
        
        int[] a = new int[20];
        for(int j = 0; j < a.length; j++){
            a[j] = j;
        }
        
        int i = 10;
        logger.logIf(Level.WARNING, ()-> i == 10, ()-> "a[10] = " + a[10]);
        logger.logIf(Level.INFO, ()-> i == 10, ()-> "a[10] = " + a[10]);     
        logger.logIf(Level.FINE, ()-> i == 10, ()-> "a[10] = " + a[10]);
    }
}



class ConditionalLogger extends Logger{
    public ConditionalLogger(String name, String resourceBundleName){
        super(name, resourceBundleName);
    }
    
    public void logIf(Level level, Supplier<Boolean> condition, Supplier<String> message){
        if(this.isLoggable(level) && condition.get()){
            this.log(level, message.get());
        }
    }
}


