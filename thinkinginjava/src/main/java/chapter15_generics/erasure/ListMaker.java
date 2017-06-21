package chapter15_generics.erasure;

/**
 * Created by xhtc on 2017/5/6.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型创建容器
 * @param <T>
 */
public class ListMaker<T> {

    List<T> create(){
        return new ArrayList<T>();
    }

    public static void main(String[] args) {
        ListMaker<String> stringMaker = new ListMaker<String>();
        List<String> stringList = stringMaker.create();
    }

}
