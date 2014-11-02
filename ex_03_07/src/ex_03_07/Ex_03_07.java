package ex_03_07;

import java.security.InvalidParameterException;
import java.util.Comparator;
import java.util.function.Function;

public class Ex_03_07 {

    public static void main(String[] args) {
        
    }
    
    public static Comparator<String> generateComparator(Order[] orders){
        if(orders == null || orders.length == 0){
            throw new InvalidParameterException("orders is invalid.");
        }
        
        Comparator<String> comparator = getComparator(orders[0]);
        
        for(int i = 1; i < orders.length; i++){
            comparator = comparator.thenComparing(getComparator(orders[i]));
        }
        
        return comparator;
    }
    
    public static Comparator<String> getComparator(Order order){
        switch (order) {
            case ORDINARY:
                //return Comparator.comparing(Function.identity());
                return Comparator.naturalOrder();
            case REVERSE:
                //return Comparator.comparing((String s) -> s).reversed();
                return Comparator.reverseOrder();
            case CASESENSITIVE:
                return String::compareTo;
            case CASEINSENSITIVE:
                return String::compareToIgnoreCase;
            case WHITESPACEOK:
                return Comparator.comparing(Function.identity());
            case NOWHITESPACE:
                return (s1, s2) -> removeWhiteSpace(s1).compareToIgnoreCase(removeWhiteSpace(s2));
            default:
                throw new AssertionError("Invalid order: " + order);
        }
    }
    
    public static String removeWhiteSpace(String s){
        return s.replace("\\s*", s);
    }
}

