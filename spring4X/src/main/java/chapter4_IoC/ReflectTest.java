package chapter4_IoC;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by xhtc on 2017/8/30.
 */
public class ReflectTest {

    public static Car initByDefaultConst() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        //通过类加载器获取Car类的对象
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Class clazz = loader.loadClass("chapter4_IoC.Car");

        //通过类的默认构造器实例化Car
        Constructor cons = clazz.getDeclaredConstructor((Class[])null);
        Car car = (Car)cons.newInstance();

        //通过反射方法设置属性
        Method setBrand = clazz.getMethod("setBrand", String.class);
        setBrand.invoke(car, "红旗A72");

        Method setColor = clazz.getMethod("setColor",String.class);
        setColor.invoke(car, "黑色");

        Method setMaxSpeed = clazz.getMethod("setMaxSpeed", int.class);
        setMaxSpeed.invoke(car, 200);

        return car;
    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Car car = initByDefaultConst();
        car.introduce();
    }

}
