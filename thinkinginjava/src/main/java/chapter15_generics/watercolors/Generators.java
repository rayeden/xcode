package chapter15_generics.watercolors;

import net.mindview.util.Generator;

import java.util.Collection;

/**
 * Created by xhtc on 2017/5/6.
 */
public class Generators {

    public static <T> Collection<T> fill(Collection<T> coll, Generator<T> gen, int n){
        for (int i = 0; i < n; i++) {
            coll.add(gen.next());
        }
        return coll;
    }


}
