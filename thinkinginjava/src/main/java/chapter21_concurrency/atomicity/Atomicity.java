package chapter21_concurrency.atomicity;

/**
 * Created by xhtc on 2017/8/8.
 */
public class Atomicity {

    int i;

    void f1(){
        i++;
    }

    void f2(){
        i += 3;
    }

}
