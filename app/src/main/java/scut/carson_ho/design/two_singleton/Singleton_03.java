package scut.carson_ho.design.two_singleton;

import android.util.Log;

/**
 * Created by JayHan on 2018/6/6.
 */

//枚举
public enum Singleton_03 {
    SINGLETON;

//    private Resource instance;
//
//    Singleton_03() {
//        instance = new Resource();
//    }
//    public Resource getInstance() {
//        return instance;
//    }

    public void doSomeThing() {
        Log.i("hzl_message", "枚举:");
    }
}
