package chapter15_generics.watercolors;

import chapter15_generics.datastruct.Sets;

import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by xhtc on 2017/5/5.
 */

/**
 * 容器中方法的区别
 */
public class ContainerMethodDifferences {

    static Set<String> methodSet(Class<?> type){
        Set<String> result = new TreeSet<String>();
        for (Method m : type.getMethods()){
            //获取类中所有的方法
            result.add(m.getName());
        }
        return result;
    }

    static void interfaces(Class<?> type){
        System.out.print("Interfaces in " + type.getSimpleName() + ": ");
        List<String> result = new ArrayList<String>();
        for(Class<?> c : type.getInterfaces())
            result.add(c.getSimpleName());

        System.out.println(result);
    }

    static Set<String> object = methodSet(Object.class);
    static {
        object.add("clone");
    }

    static void difference(Class<?> superset, Class<?> subset){
        System.out.println(superset.getSimpleName() + " extends " + subset.getSimpleName() + ", adds: ");
        //两个类方法上的区别
        Set<String> comp = Sets.difference(methodSet(superset), methodSet(subset));
        comp.removeAll(object);
        System.out.println(comp);
        interfaces(superset);
    }

    public static void main(String[] args) {
        System.out.println("Collection: " + methodSet(Collections.class));

        interfaces(Collections.class);

        difference(Set.class, Collections.class);

        difference(HashSet.class, Set.class);

        difference(LinkedHashSet.class, HashSet.class);

        difference(TreeMap.class, Map.class);

    }
}
