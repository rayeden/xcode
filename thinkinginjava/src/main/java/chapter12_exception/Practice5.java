package chapter12_exception;

/**
 * Created by xhtc on 2017/6/23.
 */

/**
 * 使用while循环建立类似“恢复模型”的异常处理行为，
 * 它将不断重复，知道异常不再抛出
 */
public class Practice5 {

    private static int count = 5;

    private static void f(int count) throws RecoverException {
        System.out.println("catch an exception." + " count = " + count);
        if (count > 0)
            throw new RecoverException("throw out recover exception");
    }

    public static void main(String[] args) {

        while (count-- > 0) {
            try {
                f(count);
            }catch (RecoverException e){
                e.printStackTrace();
                System.out.println("catching recovery exception" + ", count = " + count);
            }
        }
    }

}

class RecoverException extends Exception {

    public RecoverException() {

    }

    public RecoverException(String msg) {
        super(msg);
    }
}
