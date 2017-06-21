package chapter15_generics.base;

/**
 * Created by xhtc on 2017/5/4.
 */

import javax.xml.ws.Holder;

/**
 * 只能持有一个类型的对象
 */
public class Holders<T> {

    private T a;

    public Holders(T a){
        this.a = a;
    }

    T get(){
        return a;
    }

    public static void main(String[] args) {
        Holder<Automobile> holder = new Holder<Automobile>();
    }

}

class Automobile{};

/*
// 只能持有一种类型的对象
// 或者用Object代替Automobile持有不同类型的对象
public class Holder {

    private Automobile a;

    public Holder(Automobile a){
        this.a = a;
    }

    Automobile get(){
        return a;
    }
}
*/