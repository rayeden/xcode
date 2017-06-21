package chapter15_generics.wildcard;

/**
 * Created by xhtc on 2017/5/9.
 */
public class CovariantArrays {

    public static void main(String[] arg){
        //fruit数组的实际类型是Apple，只能放Apple或者Apple的子类
        Fruit[] fruit = new Apple[10];
        fruit[0] = new Apple();
        fruit[1] = new Jonathan();
        //运行时报错
        try {
            fruit[0] = new Fruit();
        }catch (Exception e){
            System.out.println(e);
        }

        try {
            fruit[0] = new Orange();
        }catch (Exception e){
            System.out.println(e);
        }
    }

}
