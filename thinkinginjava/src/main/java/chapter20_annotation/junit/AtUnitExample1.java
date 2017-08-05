package chapter20_annotation.junit;

/**
 * Created by xhtc on 2017/8/4.
 */

import chapter20_annotation.Test;
import net.mindview.util.*;

public class AtUnitExample1 {


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

    @Test
    static void assertTest(){
        assert 1 == 2 : "what a suprise !";
    }

    public static void main(String[] args) {
//        assertTest();
        OSExecute.command("java -version");
//        OSExecute.command("java atunit.AtUnit chapter20_annotation/AtUnitExample1");
    }
}
