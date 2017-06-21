package chapter15_generics.erasure;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by xhtc on 2017/5/6.
 */

/**
 * 泛型创建数组
 * @param <T>
 */
public class ArrayMaker<T> {

    private Class<T> kind;

    public ArrayMaker(Class<T> kind){
        this.kind = kind;
    }

    @SuppressWarnings("unchecked")
    T[] create(int size){
        //如果不用T[]强制转型，newInstance返回的只是Object，而非具体类型
        //推荐创建数组用newInstance
        return (T[])Array.newInstance(kind, size);
    }

    public static void main(String[] args) {

        ArrayMaker<String> stringMaker = new ArrayMaker<String>(String.class);

        String[] stringArray = stringMaker.create(9);

        System.out.println(Arrays.toString(stringArray));
    }

}
