package chapter21_concurrency.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * Created by xhtc on 2017/8/14.
 */

/**
 * 信号量 Semaphore，模拟池
 * @param <T>
 */
public class Pool<T> {

    private int size;

    private List<T> items = new ArrayList<>();

    //跟踪被签出的对象
    private volatile boolean[] checkedOut;

    private Semaphore available;

    public Pool(Class<T> classObject, int size) {
        this.size = size;
        checkedOut = new boolean[size];
        available = new Semaphore(size, true);
        for (int i = 0; i < size; i++) {
            try {
                //把对象加载到对象池
                items.add(classObject.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }


    public T checkOut() throws InterruptedException {
        //签出对象时，如果没有任何信号量许可证可用（即池中没有更多的对象），available将阻塞调用过程
        available.acquire();
        return getItem();
    }

    public void checkIn(T x){
        //如果被签入的对象有效，则会向信号量返回一个许可证
        if(releaseItem(x));
        available.release();
    }

    private synchronized T getItem(){
        for (int i = 0; i < size; i++) {
            if(!checkedOut[i]){
                //签出一个对象
                checkedOut[i] = true;
                return items.get(i);
            }
        }
        return null;
    }

    private synchronized boolean releaseItem(T item){
        int index = items.indexOf(item);
        if(index == -1)
            return false;
        if(checkedOut[index]){
            checkedOut[index] = false;
            return true;
        }
        return false;
    }
}
