package scut.carson_ho.rxjava_operators.rxbus;


import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by JayHan on 2018/6/27.
 */

public class RxBus {
    private final Subject<Object> mBus;

    private RxBus() {
        mBus = PublishSubject.create().toSerialized();
    }

    public static RxBus get() {
        return Holder.BUS;
    }

    public void post(Object obj) {
        mBus.onNext(obj);
    }


    private static class Holder {
        private static final RxBus BUS = new RxBus();
    }
}
