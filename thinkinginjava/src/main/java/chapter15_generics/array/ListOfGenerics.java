package chapter15_generics.array;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xhtc on 2017/5/8.
 */

/**
 * 不能创建泛型数组，一般的解决方案是在任何想要创建泛型数组的地方都用ArrayList
 * @param <T>
 */

public class ListOfGenerics<T> {

    private List<T> array = new ArrayList<T>();

    public void add(T item){
        array.add(item);
    }

    public T get(int index){
        return array.get(index);
    }

}
