package scut.carson_ho.design.two_singleton;

import android.util.Log;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * Created by JayHan on 2018/6/6.
 */

//
public final class Singleton_04 implements Serializable {
    private Singleton_04() {
    }

    private static final Singleton_04 INSTANCE = new Singleton_04();
    private static final long serialVersionUID = 0L;

    public static Singleton_04 getInstance() {
        return INSTANCE;
    }

    private Object readResolve() throws ObjectStreamException {
        return INSTANCE;
    }

}
