
package ex_06_02;

public class Ex_06_02 {


    public static void main(String[] args) {
        //増加するID列を生成するために、LongAdderはあまり役に立ちません。
        //LongAdderを共有する各threadは独自にID行列を生成していて、使う側から見ると、重複のIDが生成されてしまうからです。
    }
    
}
