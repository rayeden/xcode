/**
 * Created by Zetrov on 2017-02-11.
 *
 * Double Check
 */

public class Singleton {

    private Singleton(){}

    private static Singleton instance = null;

    public static Singleton getInstance(){
        if(instance == null) {
            synchronized (Singleton.class) {
                if(instance == null)
                    instance = new Singleton();
            }
        }
        return instance;
    }
}
