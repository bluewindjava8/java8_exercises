package ex_07_07;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.security.InvalidParameterException;

public class Ex_07_07 {
/*
    public static void main(String[] args) throws IOException, InterruptedException {
        String echo = "echo 'hello'";
        ProcessBuilder pb = new ProcessBuilder("/bin/bash", "-c", echo);
        Process process = pb.start();
        //process.waitFor();
        InputStream is = process.getInputStream();
        
//        byte[] buf = new byte[1000];
//        is.read(buf);
//        String parameter = new String(buf);
//        System.out.println("grep build " + parameter);
        
        BufferedReader in = new BufferedReader(
                new InputStreamReader(is));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
        }
        in.close();
        System.out.println(response.toString());
        
        ProcessBuilder pb2 = new ProcessBuilder("/bin/bash", "-c", "grep he >blue.txt");
        Process process2 = pb2.start();
        OutputStream os = process2.getOutputStream();
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(os));
        out.write(response.toString());
        out.close();
        
        //process2.waitFor();
        
    }
*/
    
    public static void main(String[] args) throws IOException, InterruptedException {
        pipe("echo 'hello'", "grep he");
        pipe2("echo 'hello'", "grep he");  
        pipe("find /Users/bluewind/java8_exercises/ex_07_06/src", "grep -v class", "sort");       
        pipe2("find /Users/bluewind/java8_exercises/ex_07_06/src", "grep -v class", "sort");
    }
    
    public static void pipe(String... commands) throws IOException{
        if(commands.length < 2){
            throw new InvalidParameterException("At least two commands are necessary.");
        }
        
        String previousOutput = null;
        for(int i = 0; i < commands.length; i++){
            ProcessBuilder pb = new ProcessBuilder("/bin/bash", "-c", commands[i]);
            Process process = pb.start();
            if(i > 0){
                setCommandInput(process, previousOutput);
            }
            
            previousOutput = getCommandOutput(process);
            //System.out.println(previousOutput);
        }
        System.out.println(previousOutput);

    }
    
    public static void pipe2(String... commands) throws IOException{
        if(commands.length < 2){
            throw new InvalidParameterException("At least two commands are necessary.");
        }
        
        
        Process[] processes = new Process[commands.length];
        String previousOutput = null;
        for(int i = 0; i < commands.length; i++){
            ProcessBuilder pb = new ProcessBuilder("/bin/bash", "-c", commands[i]);
            processes[i] = pb.start();
        }
        
        for(int i = 1; i < processes.length; i++){
            previousOutput = getCommandOutput(processes[i - 1]);
            setCommandInput(processes[i], previousOutput);
        }
        System.out.println(previousOutput);
    }
    
    private static String getCommandOutput(Process process) throws IOException{
        InputStream is = process.getInputStream();
        
        BufferedReader in = new BufferedReader(
                new InputStreamReader(is));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            //response.append(inputLine + System.lineSeparator());
            response.append(inputLine + '\n');
        }
        in.close();
        //System.out.println(response.toString());
        return response.toString();
    }
    
    private static void setCommandInput(Process process, String input) throws IOException{
        OutputStream os = process.getOutputStream();
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(os));
        out.write(input);
        out.close();
    }
    
}
