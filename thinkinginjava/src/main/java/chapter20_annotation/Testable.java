package chapter20_annotation;

/**
 * Created by xhtc on 2017/8/2.
 */

public class Testable {
    public void excute(){
        System.out.println("Executing");
    }

    @Test
    void testExecute(){
        excute();
    }
}
