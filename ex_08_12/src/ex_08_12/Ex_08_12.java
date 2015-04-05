
package ex_08_12;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class Ex_08_12 {


    public static void main(String[] args) {
        try {  
            for (Method method : Ex_08_12.class  
                    .getClassLoader()  
                    .loadClass(("ex_08_12.AnnotationExample"))  
                    .getMethods()) {  
                // checks if MethodInfo annotation is present for the method  
                if (method.isAnnotationPresent(TestCase.class)) {  
                    try {  
                        int param = 1;
                        Integer result = (Integer) method.invoke(null, 1);
                        System.out.println(result);
                        
                        String methodName = method.getName();
                        switch(methodName){
                            case "test1":
                                System.out.println("Method : " + methodName + " ,Expected : " + param + " , result : " + result);
                                break;
                            case "test2":
                                System.out.println("Method : " + methodName + " ,Expected : " + 2 * param + " , result : " + result);
                                break;
                            default:
                                throw new AssertionError("No such method : " + methodName);
                        }

   
                    } catch (Throwable ex) {  
                        ex.printStackTrace();  
                    }  
                }  
            }  
        } catch (SecurityException | ClassNotFoundException e) {  
            e.printStackTrace();  
        } 
    }
    
}
