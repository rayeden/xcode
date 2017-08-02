package chapter20_annotation;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by xhtc on 2017/8/2.
 * 注解处理器
 */
public class UseCaseTracker {

    /**
     * 通过反射获取注解方法
     */
    public static void trackUseCases(List<Integer> useCases, Class<?> cl){
        //获取类型方法列表
        Method[] methodList = cl.getDeclaredMethods();
        System.out.println(methodList.length);
        for(Method m :cl.getDeclaredMethods()){
            //返回指定类型的注解对象
            UseCase uc = m.getAnnotation(UseCase.class);
            if(uc != null){
                System.out.println("Found Use Case: " + uc.id() + " " + uc.description());
                useCases.remove(new Integer(uc.id()));
            }
        }
        for (int i : useCases){
            System.out.println("Warning: Missing use case-" + i);
        }
    }

    public static void main(String[] args) {
        List<Integer> useCases = new ArrayList<>();
        Collections.addAll(useCases, 47, 48, 49, 50);
        trackUseCases(useCases, PasswordUtils.class);
    }
}
