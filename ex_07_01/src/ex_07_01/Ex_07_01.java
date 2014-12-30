
package ex_07_01;

import java.time.Month;
import java.time.ZonedDateTime;


public class Ex_07_01 {


    public static void main(String[] args) {
        ZonedDateTime time = ZonedDateTime.now();
        System.out.println(time);
        
        Month month = time.getMonth();
        System.out.println(month);
    }
    
    /*
        jjs> var time = java.time.ZonedDateTime.now()
        jjs> time
        2014-12-30T20:44:04.088+09:00[Asia/Tokyo]
        jjs> var month = time.getMonth()
        jjs> month
        DECEMBER
    */
    
}
