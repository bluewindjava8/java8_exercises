package ex_08_01;

public class Ex_08_01 {

    public static void main(String[] args) {
        System.out.println(Integer.toUnsignedLong(1) + Integer.toUnsignedLong(Integer.MAX_VALUE));//2^31
        System.out.println(1 + Integer.MAX_VALUE);//-2^31
        
        System.out.println(Integer.toUnsignedLong(1) + Integer.toUnsignedLong(-1));//2^32
        System.out.println(1 + (-1));//0
        
        System.out.println(Integer.toUnsignedLong(-1) - Integer.toUnsignedLong(1));//2^32 - 2
        System.out.println(-1 - 1);// -2        
    
        System.out.println(Long.compareUnsigned(Integer.toUnsignedLong(-1), Integer.toUnsignedLong(100)));//>0
        System.out.println(Long.compare(-1, 100));//<0
        
        System.out.println(Long.divideUnsigned(Integer.toUnsignedLong(100), Integer.toUnsignedLong(-1)));//0
        System.out.println(100/(-1));//-100
        
        System.out.println(Long.remainderUnsigned(Integer.toUnsignedLong(100), Integer.toUnsignedLong(-1)));//100
        System.out.println(100%(-1));//0       
        
        //divideUnsignedとremainderUnsignedが必要な理由は符号付きと符号なしでの計算の結果が異なるからです。
    }
    
}
