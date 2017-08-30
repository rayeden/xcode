package chapter4_IoC;

/**
 * Created by xhtc on 2017/8/30.
 */

/**
 * 全盘负责委托机制，先由父类加载器寻找目标，找不到才用子类
 */
public class ClassLoaderTest {

    public static void main(String[] args) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        //默认使用AppClassLoader
        System.out.println("current loader: " + loader);
        System.out.println("parent loader: " + loader.getParent());
        //根装载器是用C++写的，负责装载JRE的核心类库，Java中看不到它
        System.out.println("grandparent loader: " +loader.getParent().getParent());
    }
}
