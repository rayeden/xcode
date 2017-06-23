package chapter12_exception;

/**
 * Created by BG289522 on 2017/6/23.
 */


public class Rethrowing {

    public static void f() throws Exception {
        System.out.println("originating the exception in f()");
        throw new Exception("thrown from f()");
    }

    /**
     * printStackTrace()方法显示原来异常抛出点的调用栈信息
     * @throws Exception
     */
    public static void g() throws Exception {
        try{
            f();
        } catch (Exception e) {
            System.out.println("Inside g(), e.printStackTrack()");
            e.printStackTrace(System.out);
            throw e;
        }
    }

    /**
     * 调用fillInStackTrace()方法，返回一个Throwable对象
     * 它是通过把当前调用栈信息填入原来那个异常对象而建立的
     * @throws Exception
     */
    public static void h() throws Exception {
        try{
            f();
        } catch (Exception e) {
            System.out.println("Inside h(), e.printStackTrack()");
            e.printStackTrace();
            //异常的新发生地
            throw (Exception)e.fillInStackTrace();
        }
    }

    public static void main(String[] args){
        try {
            g();
        } catch (Exception e) {
            System.out.println("main: printStackTrace()");
            e.printStackTrace(System.out);
        }
        try{
            h();
        } catch (Exception e) {
            System.out.println("main: printStackTrace()2");
            e.printStackTrace(System.out);
        }
    }

}
