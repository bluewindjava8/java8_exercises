
package ex_08_03;

public class Ex_08_03 {

    public static void main(String[] args) {
        // TODO code application logic here
//        System.out.println(14%21);
//        System.out.println(-14%21);
//        System.out.println(14%(-21));
        
        System.out.println(gcd(-14, 21, Remainder.getRemainder(RemainderType.TYPE_ORDINARY)));
        
        System.out.println(gcd(-14, 21, Remainder.getRemainder(RemainderType.TYPE_FLOORMOD)));
                
        System.out.println(gcd(-14, 21, Remainder.getRemainder(RemainderType.TYPE_NOMINUS)));
        
        System.out.println(gcd(21, -14, Remainder.getRemainder(RemainderType.TYPE_ORDINARY)));
        
        System.out.println(gcd(21, -14, Remainder.getRemainder(RemainderType.TYPE_FLOORMOD)));
                
        System.out.println(gcd(21, -14, Remainder.getRemainder(RemainderType.TYPE_NOMINUS)));        
    }

    
    public static int gcd(int a, int b, Remainder remainder){
        return b == 0 ? (a > 0 ? a : -a) : gcd(b, remainder.rem(a, b), remainder);
    }
    
     
}


