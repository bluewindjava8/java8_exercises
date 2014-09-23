package ex_1_10;

public class Ex_1_10 {

    public static void main(String[] args) {
        //public static <T> boolean addAll(Collection<? super T> c, T... elements)のようなmethodをCollectionのdefault methodへ
        //public static <T> int binarySearch(List<? extends Comparable<? super T>> list, T key)のようなmethodをListのdefault methodへ
        //public static final <T> List<T> emptyList()のようなmethodをListのstatic methodへ
        //public static <T> void copy(List<? super T> dest, List<? extends T> src)のようなmethodをListのstatic methodへとList のdefault methodへ両方可
        //Map, Setに関するmethodなどはListと同じような原則でMap、Setのstatic methodまたはdefault methodへ変換
    }
    
}
