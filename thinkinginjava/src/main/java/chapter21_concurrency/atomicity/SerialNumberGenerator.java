package chapter21_concurrency.atomicity;

/**
 * Created by xhtc on 2017/8/8.
 */
public class SerialNumberGenerator {

    private static volatile int serialNumber = 0;

    public synchronized static int nextSerialNumber(){
        return serialNumber++;
    }

}
