package chapter15_generics.wildcard;

import java.util.Arrays;
import java.util.List;

/**
 * Created by xhtc on 2017/5/10.
 */
public class CompilerIntelligence {

    public static void main(String[] args) {

        List<? extends Fruit> flist = Arrays.asList(new Apple());
        Apple a = (Apple) flist.get(0);

        //contains()和indexOf()接受的是Object类型的参数
        System.out.println(flist.contains(new Apple()));
        System.out.println(flist.indexOf(new Apple()));

    }

}
