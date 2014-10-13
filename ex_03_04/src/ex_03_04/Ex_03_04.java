
package ex_03_04;

import java.io.File;

public class Ex_03_04 {

    public static void main(String[] args) {
        //FilenameFilterはPredicate<T>で表現できません。（引数は二つですから）
    }
    
}

@FunctionalInterface
interface FilenameFilter{
    boolean accept(File dir, String name);
}
