package chapter15_generics.wildcard;

import java.util.Arrays;
import java.util.List;

/**
 * Created by xhtc on 2017/5/10.
 */
public class GenericReading {

    //使用精确类型的泛型
    static <T> T readExact(List<T> list){
        return list.get(0);
    }

    static List<Apple> apples = Arrays.asList(new Apple());
    static List<Fruit> fruits = Arrays.asList(new Fruit());

    static void f1(){
        Apple a = readExact(apples);
        Fruit f = readExact(fruits);

        //用Fruit对象接收Apple对象？
        f = readExact(apples);
    }

    static class Reader<T> {
        T readExact(List<T> list){
            return list.get(0);
        }
    }

    static void f2(){
        Reader<Fruit> fruitReader = new Reader<Fruit>();
        Fruit f = fruitReader.readExact(fruits);
    }

    //T作为泛型上界
    static class CovariantReader<T>{
        T readCovariant(List<? extends T> list){
            return list.get(0);
        }
    }

    static void f3(){
        CovariantReader<Fruit> fruitReader = new CovariantReader<Fruit>();
        Fruit f = fruitReader.readCovariant(fruits);
        Fruit a = fruitReader.readCovariant(apples);
    }

    public static void main(String[] args) {
        f1();
        f2();
        f3();
    }

}
