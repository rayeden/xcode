package chapter15_generics.erasure;

/**
 * Created by xhtc on 2017/5/6.
 */
public class ClassTypeCapture<T> {

    private Class<T> kind;

    public ClassTypeCapture(Class<T> kind){
        this.kind = kind;
    }

    public boolean f(Object arg){
        return kind.isInstance(arg);
    }

    public static void main(String[] args) {
//        ClassTypeCapture<Building> ctt1 = new ClassTypeCapture<>();
    }
}
