
package ex_08_03;

public interface Remainder {
    int rem(int a, int b);

    
    public static Remainder getRemainder(RemainderType type){
        switch(type){
            case TYPE_ORDINARY:
                return new OrdinaryRemainder();
            case TYPE_FLOORMOD:
                return new FloorModRemainder();
            case TYPE_NOMINUS:
                return new NoMinusRemainder();
            default:
                throw new AssertionError("Unknown type" + type);
        }
    }
}

class OrdinaryRemainder implements Remainder{

    @Override
    public int rem(int a, int b) {
        return a % b;
    }
    
}

class FloorModRemainder implements Remainder{

    @Override
    public int rem(int a, int b) {
        return Math.floorMod(a, b);
    }
    
}

class NoMinusRemainder implements Remainder{

    @Override
    public int rem(int a, int b) {
        return (a < 0 ? -a : a) % (b <0 ? -b : b);
    }
    
}