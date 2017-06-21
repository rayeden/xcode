package chapter15_generics.array;

import java.lang.reflect.Array;

/**
 * Created by xhtc on 2017/5/8.
 */

/**
 * 用类型令牌创建泛型数组，能够在擦除时恢复类型信息
 * @param <T>
 */
public class GenericArrayWithTypeToken<T> {

    private T[] array;

    @SuppressWarnings("unchecked")
    public GenericArrayWithTypeToken(Class<T> c, int sz){
        array = (T[])Array.newInstance(c, sz);
    }

    public void put(int index, T item){
        array[index] = item;
    }

    public T get(int index){
        return array[index];
    }

    public T[] rep(){
        return array;
    }

    public static void main(String[] args) {
        GenericArrayWithTypeToken<Integer> gai = new GenericArrayWithTypeToken<Integer>(Integer.class, 10);
        //带类型令牌的泛型数组可以直接被具体类型数组接收
        Integer[] ia = gai.rep();
    }

}
