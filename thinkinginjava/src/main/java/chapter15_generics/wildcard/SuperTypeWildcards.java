package chapter15_generics.wildcard;

import java.util.List;

/**
 * Created by xhtc on 2017/5/10.
 */
public class SuperTypeWildcards {

    //把Apple定为泛型的上界
    static void writeTo(List<? super Apple> apples){
        apples.add(new Apple());
        apples.add(new Jonathan());

        //编译错误
//        apples.add(new Fruit());

    }

}
