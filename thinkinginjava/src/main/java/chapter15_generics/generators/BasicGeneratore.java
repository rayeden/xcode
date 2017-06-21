package chapter15_generics.generators;

import net.mindview.util.Generator;

/**
 * Created by xhtc on 2017/5/4.
 */

/**
 * 通用的Generator，为任何类构造Generator，用于快速生成该类的对象
 * @param <T>
 */
public class BasicGeneratore<T> implements Generator<T> {

    private Class<T> type;

    @Override
    public T next() {
        try{
            return type.newInstance();
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

    public BasicGeneratore(Class<T> type){
        this.type = type;
    }

    public static <T> Generator<T> create(Class<T> type){
        return new BasicGeneratore<T>(type);
    }

}
