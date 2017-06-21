package chapter15_generics.base;

/**
 * Created by xhtc on 2017/5/4.
 */

/**
 *  用泛型方法代替泛型类，使用更灵活
 */
public class Tupple {

    public static <A, B> TwoTuple<A, B> tuple(A a, B b){
        return new TwoTuple<A, B>(a, b);
    }

    public static <A, B, C> ThreeTuple<A, B, C> tuple(A a, B b, C c){
        return new ThreeTuple<A, B, C>(a, b, c);
    }
}
