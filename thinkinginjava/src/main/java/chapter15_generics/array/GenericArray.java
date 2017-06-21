package chapter15_generics.array;

/**
 * Created by xhtc on 2017/5/8.
 */
public class GenericArray<T> {

    //集合内部声明泛型数组
    private T[] array;

    @SuppressWarnings("unchecked")
    public GenericArray(int sz){
        //用Object创建数组后转型
        array = (T[])new Object[sz];
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
        GenericArray<Integer> gai = new GenericArray<Integer>(10);
        //泛型数组在运行时仍是Object类型，不能直接用Integer[]接收
//        Integer[] ia = gai.rep();
        Object[] oa = gai.rep();
    }

}
