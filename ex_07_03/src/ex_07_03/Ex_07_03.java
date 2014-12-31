

package ex_07_03;


public class Ex_07_03 {


    public static void main(String[] args) {
    
    }
    
}

/*
jjs> var b = new java.math.BigInteger('1234567890987654321')
jjs> b
1234567890987654400
jjs> b.mod(java.math.BigInteger.TEN)
1
b自体はBigIntegerですが、表示時にjavaのdoubleに相当するjavascriptのNumberとして表示され、
精度が失うことになります。

元の数字をそのまま表示したい場合、下記のように書きます。
jjs> java.lang.String.format("%d", b.longValueExact())
1234567890987654321
*/
