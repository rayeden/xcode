package chapter15_generics.makeupoferasure;

import static net.mindview.util.Print.print;

/**
 * Created by xhtc on 2017/5/6.
 */

/**
 * 并不建议用这种隐式的工厂创建类型对象
 */
public class InstantiateGenericType {
    public static void main(String[] args) {
        ClassAsFactory<Employee> fe = new ClassAsFactory<Employee>(Employee.class);
        print("ClassAsFactory<Employee> succeeded");
        try{
            ClassAsFactory<Integer> fi = new ClassAsFactory<Integer>(Integer.class);
        }catch (Exception e){
            print("ClassAsFactory<Integer> failed");
        }
    }
}

class ClassAsFactory<T>{
    T x;
    public ClassAsFactory(Class<T> kind){
        try{
            x = kind.newInstance();
        }catch (Exception e){
            throw new RuntimeException();
        }
    }
}

class Employee{}
