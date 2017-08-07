package chapter21_concurrency.basicthreading;

import java.util.concurrent.Callable;

/**
 * Created by xhtc on 2017/8/5.
 */

/**
 * 带返回值的Thread
 */
public class TaskWithResult implements Callable<String> {

    private int id;

    public TaskWithResult(int id) {
        this.id = id;
    }

    @Override
    public String call() throws Exception {
        return "result of TaskWithResult " + id;
    }

}
