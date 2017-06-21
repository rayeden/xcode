package chapter15_generics.array;

/**
 * Created by xhtc on 2017/5/8.
 */

/**
 * 创建泛型数组的唯一方式就是创建一个被擦除类型的新数组，然后对其转型
 */
public class ArrayOfGeneric {

    static final int SIZE = 100;

    //不能这样定义泛型数组
    static Generic<Integer>[] gia;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        gia = (Generic<Integer>[])new Generic[SIZE];
        gia[0] = new Generic<Integer>();
    }

}


class Generic<T>{}