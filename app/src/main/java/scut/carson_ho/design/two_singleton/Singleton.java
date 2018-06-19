package scut.carson_ho.design.two_singleton;

/**
 * Created by JayHan on 2018/6/6.
 */

//DCL
public class Singleton {
    private volatile static Singleton mSingleton = null;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (mSingleton == null) {
            synchronized (Singleton.class) {
                if (mSingleton == null) {
                    mSingleton = new Singleton();
                }
            }
        }
        return mSingleton;
    }
}
