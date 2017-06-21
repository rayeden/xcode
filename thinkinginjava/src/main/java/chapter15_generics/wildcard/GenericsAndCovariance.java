package chapter15_generics.wildcard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xhtc on 2017/5/10.
 */

/**
 *  List<\? extends Fruit>可以合法地指向一个List<\Orange>，
 *  但是一旦执行，就会丢失掉传递任何对象的能力，甚至是Object也不能被传递
 */
public class GenericsAndCovariance {

    public static void main(String[] args) {

        List<? extends Fruit> flist = new ArrayList<Apple>();

        //编译错误
//        flist.add(new Apple());
//        flist.add(new Fruit());
//        flist.add(new Object());

        flist.add(null);

        //调用返回Fruit的方法则是安全的，因为这个List中的任何对象至少具有Fruit类型
        Fruit f = flist.get(0);

    }

}
