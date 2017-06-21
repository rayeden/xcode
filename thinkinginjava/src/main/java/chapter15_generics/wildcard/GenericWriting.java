package chapter15_generics.wildcard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xhtc on 2017/5/10.
 */

/**
 * 逆变，超类型通配符
 */
public class GenericWriting {

    static <T> void writeExact(List<T> list, T item){
        list.add(item);
    }

    static List<Apple> apples = new ArrayList<Apple>();
    static List<Fruit> fruits = new ArrayList<Fruit>();

    static void f1(){
        writeExact(apples, new Apple());
    }

    static <T> void writeWithWildcard(List<? super T> list, T item){
        list.add(item);
    }

    static void f2(){
        writeExact(apples, new Apple());
        writeExact(fruits, new Apple());
    }

    public static void main(String[] args) {
        f1();
        f2();
    }
}
