
package ex_09_11;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Ex_09_11 {

    public static void main(String[] args) throws InterruptedException, IOException {
        //grep("tt", "\\(^\\|[^0-9]\\)\\{1\\}\\([345]\\{1\\}[0-9]\\{3\\}\\|6011\\)\\{1\\}[-]\\?[0-9]\\{4\\}[-]\\?\\[0-9]\\{2\\}[-]\\?[0-9]\\{2\\}-\\?[0-9]\\{1,4\\}\\($\\|[^0-9]\\)\\{1\\}");
        String home = System.getProperty("user.home");
        grep("tt", "\\([345]\\{1\\}[0-9]\\{3\\}\\|6011\\)\\{1\\}[ -]\\?[0-9]\\{4\\}[ -]\\?[0-9]\\{2\\}[-]\\?[0-9]\\{2\\}[ -]\\?[0-9]\\{1,4\\}");
    }
    
    public static void grep(String rootPath, String patternStr) throws IOException, InterruptedException{
        //Stream<Path> entries = Files.walk(rootPath);
        ProcessBuilder builder = new ProcessBuilder("grep", "-o", "-r", patternStr, rootPath);
        //builder.inheritIO();
        builder.redirectOutput(Paths.get("result.txt").toFile());
        builder.start().waitFor();
    }
}
