package scut.carson_ho.design.two_singleton;

/**
 * Created by JayHan on 2018/6/6.
 */

//静态内部类
public class Singleton_02 {

    private Singleton_02() {
    }

    public static Singleton_02 getInstance() {
        return SingletonHolder.singleton;
    }

    private static class SingletonHolder {
        private static final Singleton_02 singleton = new Singleton_02();
    }
}
