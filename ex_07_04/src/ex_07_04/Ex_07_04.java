package ex_07_04;

public class Ex_07_04 {

    public static void main(String[] args) {

    }
    
}

/*

jjs> var str = "hello,world".slice(3)
jjs> str
lo,world
jjs> str.getClass()
class java.lang.String

java.lang.String.class.cast(str.getClass())
java.lang.ClassCastException: Cannot cast java.lang.Class to java.lang.String

java.lang.String.class.castは渡される引数をStringに変換するmethodですので、引数はClass<String>ですから、変換は失敗したわけです。

正しい使い方は以下に示しています。
jjs> java.lang.String.class.cast(str)
lo,world

*/
