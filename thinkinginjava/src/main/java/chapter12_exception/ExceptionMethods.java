package chapter12_exception;

/**
 * Created by xhtc on 2017/6/23.
 */
public class ExceptionMethods {

    public static void main(String[] args) {
        try{
            throw new Exception("My Exception");
        } catch (Exception e){
            System.out.println("Count Exception");
            System.out.println("getMessage(): " + e.getMessage());
            System.out.println("getLocalizedMessage(): " + e.getLocalizedMessage());
            System.out.println("toString(): " + e);
            System.out.println("printStackTrace(): ");
            e.printStackTrace(System.out);
        }
    }
}
