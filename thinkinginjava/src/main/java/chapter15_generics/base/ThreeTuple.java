package chapter15_generics.base;

/**
 * Created by xhtc on 2017/5/4.
 */
public class ThreeTuple<A, B, C> extends TwoTuple<A, B> {

    public final C third;

    public ThreeTuple(A a, B b, C c) {
        super(a, b);
        third = c;

    }

    public String toString(){

        return "(" + first + "," + second + "," + third + ")";

    }

}
