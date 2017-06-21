package chapter15_generics.makeupoferasure;

/**
 * Created by xhtc on 2017/5/6.
 */
public class CreatorGeneric<T> {

    public static void main(String[] args) {
        Creator c = new Creator();
        c.f();
    }

}

abstract class GenericWithCreate<T>{

    final T element;

    GenericWithCreate(){
        element = create();
    }
    abstract T create();

}

class X {}

class Creator extends GenericWithCreate<X> {

    @Override
    X create() {
        return new X();
    }

    void f(){
        System.out.println(element.getClass().getSimpleName());
    }
}