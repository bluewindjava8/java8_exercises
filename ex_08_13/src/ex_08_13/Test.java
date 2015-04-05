
package ex_08_13;

public class Test {

    public static void main(String[] args) {
	Integer result = 0;
	result = (Integer)ex_08_13.AnnotationExample.test1( 1 );
	System.out.println("result : " + result + " , expected : " +1 );
	result = (Integer)ex_08_13.AnnotationExample.test2( 1 );
	System.out.println("result : " + result + " , expected : " + 2 );
    }
    
}
