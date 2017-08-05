package chapter20_annotation;

/**
 * Created by xhtc on 2017/8/4.
 */
import net.mindview.atunit.AtUnit;
import net.mindview.util.*;

public class AtUnitExample1 {

    private AtUnit atUnit;

    public String methodOne(){
        return "This is methodOne.";
    }

    public int methodTwo(){
        return 2;
    }

    @Test
    boolean methodOneTest(){
        return methodOne().equals("This is methodOne");
    }

    @Test
    boolean methodOneTwo(){
        return methodTwo() == 2;
    }

    public static void main(String[] args) {
        OSExecute.command("java net.mindview.atunit.AtUnit chapter20_annotation/AtUnitExample1");
    }
}
