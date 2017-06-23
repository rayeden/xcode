package chapter12_exception;

/**
 * Created by xhtc on 2017/6/23.
 */

class SimpleException extends Exception {
}

public class InheritingExceptions extends Exception {

    public void f() throws SimpleException{
        System.out.println("Throw SimpleException from f()");
        throw new SimpleException();
    }

    public static void main(String[] args){
        InheritingExceptions sed = new InheritingExceptions();
        try{
            sed.f();
        } catch (SimpleException e) {
            System.out.println("Caught it!");
        }
    }

}
