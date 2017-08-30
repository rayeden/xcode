package chapter4_IoC;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by xhtc on 2017/8/30.
 */
public class PrivateCarReflect {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
        //类加载器创建实例
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Class clazz = loader.loadClass("chapter4_IoC.PrivateCar");

        PrivateCar car = (PrivateCar)clazz.newInstance();
        Field colorField = clazz.getDeclaredField("color");

        //检查域的类型
        if(colorField.getType().equals(String.class)){
            System.out.println("color field is String type");
            colorField.setAccessible(true);
            if(colorField.get(car) == null){
                System.out.println("color in car is null");
            }
        }

        //取消Java语言访问检查，以此访问private域
        colorField.setAccessible(true);
        colorField.set(car,"红色");

        Method driveCar = clazz.getDeclaredMethod("drive", (Class[])null);
        driveCar.setAccessible(true);
        driveCar.invoke(car, (Object[])null);

    }
}
