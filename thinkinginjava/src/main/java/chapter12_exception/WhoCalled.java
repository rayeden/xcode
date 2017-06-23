package chapter12_exception;

/**
 * Created by BG289522 on 2017/6/23.
 */

/**
 * getStackTrace()方法返回一个由展柜集中的元素所构成的数组，
 * 每个元素都表示栈中的一个帧。
 *
 * 元素0是栈顶元素，并且是调用序列中的最后一个方法调用。
 * 数组的最后一个元素和栈底是调用序列中的第一个方法调用。
 */
public class WhoCalled {

    /**
     * 捕获异常，打印异常堆栈
     */
    static void f(){
        try{
            throw new Exception();
        } catch (Exception e){
            for (StackTraceElement ste : e.getStackTrace())
                System.out.println(ste.getMethodName());
        }
    }

    static void g(){
        f();
    }

    static void h(){
        g();
    }

    public static void main(String[] args) {
        f();
        System.out.println("---------------------");
        g();
        System.out.println("---------------------");
        h();
    }

}
