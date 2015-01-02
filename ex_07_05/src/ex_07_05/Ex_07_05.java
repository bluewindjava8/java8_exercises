
package ex_07_05;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Ex_07_05 {

    public static void main(String[] args) throws IOException, ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("nashorn");
        Object result = engine.eval(Files.newBufferedReader(Paths.get("src/myscript.js")));
        System.out.println(result);
    }
    
}
