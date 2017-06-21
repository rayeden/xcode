package chapter15_generics.array;

/**
 * Created by xhtc on 2017/5/8.
 */
public class GenericArray2<T> {

    //集合内部直接声明Object数组
    private Object[] array;

    public GenericArray2(int sz){
        array = new Object[sz];
    }

    public void put(int index, T item){
        array[index] = item;
    }

    @SuppressWarnings("unchecked")
    public T get(int index){
        return (T)array[index];
    }

    @SuppressWarnings("unchecked")
    public T[] rep(){
        return (T[])array;
    }

    public static void main(String[] args) {
        GenericArray2<Integer> gai = new GenericArray2<Integer>(10);
        for (int i = 0; i < 10; i++) {
            gai.put(i, i);
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(gai.get(i) + " ");
        }
        System.out.println();
        try{
            //依然无法用Integer[]数组接收，泛型数组底层永远是Object[]
            Integer[] ia = gai.rep();
        }catch (Exception e){
            System.out.println(e);
        }
    }

}
