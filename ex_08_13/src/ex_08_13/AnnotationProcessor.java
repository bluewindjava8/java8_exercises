package ex_08_13;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;


public class AnnotationProcessor {
    public void process(Class klass) throws FileNotFoundException, IOException{
        //BufferedReader headerReader = new BufferedReader(new FileReader("header.part"));
//        BufferedReader headerReader = Files.newBufferedReader(Paths.get("header.part"));
//        BufferedReader tailReader = new BufferedReader(new FileReader("tail.part")); 
//        BufferedWriter writer = new BufferedWriter(new FileWriter("Test.java"));
//        
//        String currentLine;
//        while ((currentLine = headerReader.readLine()) != null) {
//                System.out.println(currentLine);
//            writer.write(currentLine);
//            writer.newLine();
//        }
        
        try(    BufferedReader headerReader = Files.newBufferedReader(Paths.get("header.part"));
                BufferedReader tailReader = new BufferedReader(new FileReader("tail.part")); 
                BufferedWriter writer = new BufferedWriter(new FileWriter("src/ex_08_13/Test.java"));
            ){  
            
            String currentLine;
            while ((currentLine = headerReader.readLine()) != null) {
                System.out.println(currentLine);
                writer.write(currentLine);
                writer.newLine();
            }
            
            String className = "ex_08_13.AnnotationExample";
            
            writer.write("\tInteger result = 0;");
            writer.newLine();
            
            for (Method method : Ex_08_13.class.getClassLoader().loadClass(className).getMethods()) {  
                // checks if MethodInfo annotation is present for the method  
                int param = 1;
                if (method.isAnnotationPresent(TestCase.class)) {  
                    try {  
                        //System.out.println(className + "." + method.getName() + "( " + param + " );");
                        String methodName = method.getName();

                        
                        writer.write("\t" + "result = (Integer)"+ className + "." + methodName + "( " + param + " );");
                        writer.newLine();
                        
                        
                        String info = "";
                        switch(methodName){
                            case "test1":
                                info = "\tSystem.out.println(\"result : \" + result + \" , expected : \" +" + param +" );";
                                break;
                            case "test2":
                                info = "\tSystem.out.println(\"result : \" + result + \" , expected : \" + " + param * 2 +" );";                                break;
                            default:
                                throw new AssertionError("No such method : " + methodName);
                        }
                        writer.write(info);
                        writer.newLine();
                        
                                


   
                    } catch (Throwable ex) {  
                        ex.printStackTrace();  
                    }  
                }  
            }  
            
            while ((currentLine = tailReader.readLine()) != null) {
                //System.out.println(sCurrentLine);
                //System.out.println(currentLine);
                writer.write(currentLine);
                writer.newLine();
            }
        } catch (SecurityException | ClassNotFoundException e) {  
            e.printStackTrace();  
        } 
        
//        while ((currentLine = tailReader.readLine()) != null) {
//                //System.out.println(sCurrentLine);
//            System.out.println(currentLine);
//            writer.write(currentLine);
//            writer.newLine();
//        }
        
//        writer.close();
        
    }
}
