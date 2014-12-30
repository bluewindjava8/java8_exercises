
package ex_07_02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class Ex_07_02 {


    public static void main(String[] args) throws FileNotFoundException {
//        String input = "1   fish 2 fish red fish blue fish";
//        Scanner s = new Scanner(input);//.useDelimiter("\\s*fish\\s*");
//        System.out.println(s.next());
//        System.out.println(s.next());
//        System.out.println(s.next());
//        System.out.println(s.next());
//        s.close();
        
        Scanner s = new Scanner(new File("test.txt")).useDelimiter("[\\P{L}]+");
        SortedSet<String> set =new TreeSet<>();
        while (s.hasNext()) {
          String str = s.next();
          //System.out.println(str);
          if(str.length() > 12)
            set.add(str);
          
      }
      
      System.out.println(set);
        
        
    }
}
    
    /*
        jjs> var s = new java.util.Scanner(new java.io.File("test.txt")).useDelimiter("[\\P{L}]+")
        jjs> var set = new java.util.TreeSet() 
        jjs> while(s.hasNext()){ var str = s.next(); if(str.length > 12) set.add(str);}
        true
        jjs> set
        [circumstances, findWithinHorizon, independently]
    */
    
    /*
        jjs> var s = new java.util.Scanner(new java.io.File("test.txt")).useDelimiter("[\\P{L}]+")
        jjs> var set = new java.util.TreeSet()
        jjs> while(s.hasNext()){ var str = s.next(); java.lang.System.out.println(str + ': select ? y/n'); var line = java.lang.System.console().readLine(); if(line.equals('y')) set.add(str);}
        The: select ? y/n
        n
        findInLine: select ? y/n
        n
        java: select ? y/n
        n
        lang: select ? y/n
        n
        String: select ? y/n
        n
        findWithinHorizon: select ? y/n
        y
        java: select ? y/n
        n
        lang: select ? y/n
        n
        String: select ? y/n
        n
        int: select ? y/n
        n
        and: select ? y/n
        n
        skip: select ? y/n
        n
        java: select ? y/n
        n
        util: select ? y/n
        n
        regex: select ? y/n
        n
        Pattern: select ? y/n
        n
        methods: select ? y/n
        n
        operate: select ? y/n
        n
        independently: select ? y/n
        y
        of: select ? y/n
        n
        the: select ? y/n
        n
        delimiter: select ? y/n
        n
        pattern: select ? y/n
        n
        These: select ? y/n
        n
        methods: select ? y/n
        n
        will: select ? y/n
        n
        attempt: select ? y/n
        n
        to: select ? y/n
        n
        match: select ? y/n
        n
        the: select ? y/n
        n
        specified: select ? y/n
        n
        pattern: select ? y/n
        n
        with: select ? y/n
        n
        no: select ? y/n
        n
        regard: select ? y/n
        n
        to: select ? y/n
        n
        delimiters: select ? y/n
        n
        in: select ? y/n
        n
        the: select ? y/n
        n
        input: select ? y/n
        n
        and: select ? y/n
        n
        thus: select ? y/n
        n
        can: select ? y/n
        n
        be: select ? y/n
        n
        used: select ? y/n
        n
        in: select ? y/n
        n
        special: select ? y/n
        n
        circumstances: select ? y/n
        y
        where: select ? y/n
        n
        delimiters: select ? y/n
        n
        are: select ? y/n
        n
        not: select ? y/n
        n
        relevant: select ? y/n
        n
        These: select ? y/n
        n
        methods: select ? y/n
        n
        may: select ? y/n
        n
        block: select ? y/n
        n
        waiting: select ? y/n
        n
        for: select ? y/n
        n
        more: select ? y/n
        n
        input: select ? y/n
        n
        null
        jjs> set
        [circumstances, findWithinHorizon, independently]
    
*/
