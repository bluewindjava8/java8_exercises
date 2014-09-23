package ex_1_11;

public class Ex_1_11 {

    public static void main(String[] args) {
        new Test().f();
    }
    
}

interface IAbstract {
    void f();
}

interface IDefault{
    default void f(){
        System.out.println("IDefault.f()");
    }
}

interface IStatic{
    static void f(){
        
    }
}

interface JAbstract{
    void f();
}

interface JDefault{
    default void f(){
        
    }
}

interface JStatic{
    static void f(){
        
    }
}

//fを具象クラス内で実装しなければなりません
class TestIAJA implements IAbstract, JAbstract {

    @Override
    public void f() {
    }
    
}

//衝突発生のため、fをクラス内で実装しなければなりません
class TestIAJD implements IAbstract, JDefault {

    @Override
    public void f() {
    }
    
}

//fを具象クラス内で実装しなければなりません
class TestIAJS implements IAbstract, JStatic{

    @Override
    public void f() {
    }
    
}

//衝突発生のため、fをクラス内で実装しなければなりません
class TestIDJA implements IDefault, JAbstract{
    
    @Override
    public void f() {
    } 
    
}

//衝突発生のため、fをクラス内で実装しなければなりません
class TestIDJD implements IDefault, JDefault{
    
    @Override
    public void f() {
    } 
}

//IDefaultのfが継承されます
class TestIDJS implements IDefault, JStatic{
    
}

//fを具象クラス内で実装しなければなりません
class TestISJA implements IStatic, JAbstract{
        
    @Override
    public void f() {
    } 
    
}

//JDefaultのfが継承されます
class TestISJD implements IStatic, JDefault{
    
    
}

//実装すべきmethodがない
class TestISJS implements IStatic, JStatic{
    
}

class S {
    public void f(){
        System.out.println("S.f()");
    }
}

//Sのf()が継承され、　IDefault.f()が無視される
class Test extends S implements IDefault {
    
} 