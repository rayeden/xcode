package chapter12_exception;

/**
 * Created by BG289522 on 2017/6/23.
 */

class OneException extends Exception {
    public OneException(String s) {
        super(s);
    }
}

class TwoException extends Exception {
    public TwoException(String s) {
        super(s);
    }
}

public class RethrowNew {

    public static void f() throws OneException {
        System.out.println("originating the exception in f()");
        throw new OneException("thrown from f()");
    }

    public static void main(String[] args) throws TwoException {
        try {
            try {
                f();
            } catch (OneException e) {
                System.out.println("Caught in inner try, e.printStackTrace()");
                e.printStackTrace();
                throw new TwoException("from inner try");
            }
            //重新抛出的异常，只知道自己来自main，而对f()一无所知
        } catch (TwoException e) {
            System.out.println("Caught in outer try, e.printStackTrace()");
            e.printStackTrace();
        }
    }
}
