package ex_08_13;




public class AnnotationExample {
 
    @TestCase
    public static int test1(int a){
        return a;
    }
    
    @TestCase
    public static int test2(int a){
        return 2 * a;
    }   
    
    public static int test3(int a){
        return 3 * a;
    }
}
