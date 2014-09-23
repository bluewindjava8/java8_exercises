package ex_02_01;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Ex_02_01 {

    public static void main(String[] args)throws Exception {
        String contents = new String(Files.readAllBytes(Paths.get("alice.txt")),StandardCharsets.UTF_8);
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));
            
        long count = words.parallelStream().filter(w -> w.length() > 12).count();
        System.out.println(count);
    }
    
    //thread間の同期を自分で保証しなければならないため、単一カウンターを更新するためにスレッドを使いたくない。
    
}
