package chapter15_generics.generators;

import net.mindview.util.*;

/**
 * Created by xhtc on 2017/5/4.
 */
public class BasicGeneratorDemo {

    public static void main(String[] args) {

        Generator<CountedObject> gen = BasicGeneratore.create(CountedObject.class);
//        Generator<CountedObject> gen = new BasicGeneratore(CountedObject.class);

        for (int i = 0; i < 5; i++) {
            System.out.println(gen.next());
        }
    }
}
