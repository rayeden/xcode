package chapter15_generics.makeupoferasure;

/**
 * Created by xhtc on 2017/5/6.
 */

/**
 * 显示工厂，获得编译器检查
 */
public class FactoryConstraint {

    public static void main(String[] args) {

        new Foo2<Integer>(new IntegerFactory());
        new Foo2<Widget>(new Widget.Factory());
    }
}

/**
 * 内建工厂对象
 * @param <T>
 */
interface FactoryI<T>{
    T create();
}

class Foo2<T> {
    private T x;
    public <F extends FactoryI<T>> Foo2(F factory){
        x = factory.create();
    }
}

class IntegerFactory implements FactoryI<Integer> {
    @Override
    public Integer create() {
        return new Integer(0);
    }
}

class Widget{

    public static class Factory implements FactoryI<Widget> {
        @Override
        public Widget create() {
            return new Widget();
        }
    }
}