package chapter5_generics;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BG289522 on 2017/5/8.
 */

/**
 * 有一个同步列表和函数，编写方法reduct并用apply来处理这个列表
 * 假设函数是用来做两个整数的和，那么reduce方法就会返回列表中所有值的和
 */
public class ArraysOfGeneric<E> {

    /**
     * 没有泛型时的代码
     * @param list 同步列表
     * @param f 方法
     * @param initVal 初始值
     * @return
     */
    static Object reduce(List list, Function f, Object initVal){
        synchronized (list){
            Object result = initVal;
            for(Object o : list)
                result = f.apply(result, o);
            return result;
        }
    }

    /**
     * 67条，不要在同步区域中调用外来方法，
     * 因此在持有锁的时候修改reduce方法来复制列表中的内容去进行操作，
     * 一般利用List的toArray方法
     * @param list
     * @param f
     * @param initVal
     * @return 泛型报错
     */
//    static Object reduce2(List list, Function f, Object initVal){
//        //内部锁定列表
//        Object[] snapshot = list.toArray();
//        Object result = initVal;
//        for(E e: snapshot){
//            result = f.apply(result, e);
//        }
//        return result;
//    }

    /**
     * 泛型方法，可运行，但是不安全
     * 因为泛型无法在运行时检查转换的安全性 E[] snapshot = (E[])list.toArray();
     * @param list
     * @param f
     * @param initVal
     * @param <E>
     * @return
     */
    static <E> E reduce3(List<E> list, Function2<E> f, E initVal){
        //内部锁定列表
        E[] snapshot = (E[])list.toArray();
        E result = initVal;
        for(E e : snapshot)
            result = f.apply(result, e);
        return result;
    }

    /**
     * 用列表代替数组，损失一些性能或简洁性，换回更高的类型安全性和互用性
     * @param list
     * @param f
     * @param initVal
     * @param <E>
     * @return
     */
    static <E> E reduce4(List<E> list, Function2<E> f, E initVal){
        List<E> snapshot;
        synchronized (list){
            snapshot = new ArrayList<E>(list);
        }
        E result = initVal;
        for(E e : snapshot)
            result = f.apply(result, e);
        return result;
    }

}

interface Function{
    Object apply(Object arg1, Object arg2);
}

interface Function2<T>{
    T apply(T arg1, T arg2);
}
