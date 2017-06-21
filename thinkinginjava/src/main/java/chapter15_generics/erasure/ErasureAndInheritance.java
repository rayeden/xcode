package chapter15_generics.erasure;

/**
 * Created by xhtc on 2017/5/6.
 */
public class ErasureAndInheritance {

    //此注解消除调用泛型方法时的警告
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Derived2 d2 = new Derived2();
        Object obj = d2.get();
        d2.set(obj);
    }

}

class GenericBase<T> {

    private T element;

    public void set(T arg){
        arg = element;
    }

    public T get(){
        return element;
    }
}

class Derived1<T> extends GenericBase<T> {}

class Derived2 extends GenericBase {}

//class Derived3 extends GenericBase<?>{}
