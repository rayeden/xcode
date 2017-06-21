package chapter15_generics.wildcard;

/**
 * Created by xhtc on 2017/5/10.
 */
public class Holder<T> {

    private T value;
    public Holder(){}
    public Holder(T val){
        this.value = val;
    }
    public void set(T val){
        value = val;
    }
    public T get(){
        return value;
    }

    public boolean equals(Object obj){
        return value.equals(obj);
    }

    public static void main(String[] args) {

        Holder<Apple> Apple = new Holder<chapter15_generics.wildcard.Apple>(new Apple());
        chapter15_generics.wildcard.Apple d = Apple.get();
        Apple.set(d);

//        Holder<Fruit> fruit = Apple;

        //Fruit作为泛型的上界
        Holder<? extends Fruit> fruit = Apple;

        //调用get返回的是Fruit
        Fruit p = fruit.get();
        //可以通过向下转型获取Apple
        d = (chapter15_generics.wildcard.Apple)fruit.get();

        try{
            //Orange也是Fruit的子类，所以可以编译通过
            //但执行时编译器发现fruit存放的是Apple，因此转型失败
            Orange c = (Orange)fruit.get();
        }catch (Exception e){
            System.out.println(e);
        }

        //equals接收的是Object类型而不是T类型的函数
        System.out.println(fruit.equals(d));
    }

}
