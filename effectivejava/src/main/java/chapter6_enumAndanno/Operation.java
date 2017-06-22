package chapter6_enumAndanno;

/**
 * Created by BG289522 on 2017/6/21.
 */

import java.util.HashMap;
import java.util.Map;

/**
 * 特定于常量的方法实现
 */
public enum Operation {

    PLUS("+"){
        @Override
        double apply(double x, double y) {
            return x + y;
        }
    },

    MINUS("-"){
        @Override
        double apply(double x, double y) {
            return x - y;
        }
    },

    TIMES("*"){
        @Override
        double apply(double x, double y) {
            return x * y;
        }
    },

    DIVIDE("/"){
        @Override
        double apply(double x, double y) {
            return x / y;
        }
    };

    abstract double apply(double x, double y);

    private final String symbol;

    Operation(String symbol){
        this.symbol = symbol;
    }

    @Override
    public String toString(){
        return symbol;
    }

    private static final Map<String, Operation> stringToEnum = new HashMap<String, Operation>();

    static {
        for (Operation op : values()){
            stringToEnum.put(op.toString(), op);
        }
    }

    public static Operation fromString(String symbol){
        return stringToEnum.get(symbol);
    }


    public static void main(String[] args) {
        double x = Double.parseDouble("5.0");
        double y = Double.parseDouble("3.0");

        for(Operation op : Operation.values()){
            System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x, y));
        }
    }

    //    PLUS, MINUS, TIMES, DIVIDE;
//
//    double apply(double x, double y){
//        switch (this){
//            case PLUS: return x + y;
//            case MINUS: return x - y;
//            case TIMES: return x * y;
//            case DIVIDE: return x / y;
//        }
//        throw new AssertionError("Unknown operation: " + this);
//    }

}
