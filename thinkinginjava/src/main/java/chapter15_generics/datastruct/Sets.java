package chapter15_generics.datastruct;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by xhtc on 2017/5/5.
 */
public class Sets {

    /**
     * 合并两个参数
     * @return
     */
    public static <T> Set<T> union(Set<T> a, Set<T> b){
        //new一个HashSet，返回一个全新对象
        Set<T> result = new HashSet<T>(a);
        result.addAll(b);
        return result;
    }

    /**
     * 返回两个参数的公共部分
     * @return
     */
    public static <T> Set<T> intersection(Set<T> a, Set<T> b){
        Set<T> result = new HashSet<T>(a);
        result.retainAll(b);
        return result;
    }

    /**
     * 从a中移除b包含的元素，获得两个元素的差值
     * @return
     */
    public static <T> Set<T> difference(Set<T> a, Set<T> b){
        Set<T> result = new HashSet<T>(a);
        result.removeAll(b);
        return result;
    }

    /**
     * 返回两个集合的交集之外的所有元素
     * @return
     */
    public static <T> Set<T> complement(Set<T> a, Set<T> b){
        return difference(union(a, b), intersection(a, b));
    }

}
