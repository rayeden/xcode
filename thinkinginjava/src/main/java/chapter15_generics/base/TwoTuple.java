package chapter15_generics.base;

/**
 * Created by xhtc on 2017/5/4.
 */

/**
 * 元组，把一组对象直接打包存储与其中一个单一对象
 *
 * @param <A>
 * @param <B>
 */
public class TwoTuple<A, B> {

    //这个容器对象允许读，但是不允许写
    public final A first;

    public final B second;

    public TwoTuple(A a, B b){

        first = a;

        second = b;
    }

    public String toString(){

        return "(" + first + "," + second + ")";

    }

}
