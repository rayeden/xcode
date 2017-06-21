package chapter15_generics.erasure;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xhtc on 2017/5/6.
 */
public class FilledListMaker<T> {

    List<T> create(T t, int n) {

        List<T> result = new ArrayList<T>();

        for (int i = 0; i < n; i++) {
            result.add(t);
        }

        return result;

    }

    public static void main(String[] args) {
        FilledListMaker<String> stringMaker = new FilledListMaker<String>();
        List<String> list = stringMaker.create("Hello", 4);
        System.out.println(list);
    }

}
