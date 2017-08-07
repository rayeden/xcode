package chapter21_concurrency.sharedresources;

/**
 * Created by xhtc on 2017/8/7.
 */
public abstract class IntGenerator {

    //boolean类型，canceled是原子性的，即诸如赋值和返回值这样的简单操作在发生时没有中断的可能
    //因此看不到这个域处于在执行这些见到操作过程中的中间状态，为了保证可见性，canceled被标记为volatile
    private volatile boolean canceled = false;

    public abstract int next();

    public void cancel(){
        canceled = true;
    }

    public boolean isCanceled(){
        return canceled;
    }

}
