package scut.carson_ho.rxjava_operators.rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import scut.carson_ho.rxjava_operators.R;

/**
 * Created by JayHan on 2018/2/8. 功能性操作符
 */

public class FunctionActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "RxJava";

    private Button mFunction;
    private TextView mShowMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function);

        mFunction = (Button) findViewById(R.id.function);
        mShowMessage = (TextView) findViewById(R.id.showMessage);

        mFunction.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.function:
                functionMethod();
                break;
        }
    }

    /**
     * 线程控制（也称为调度 / 切换）实例
     * 指定 被观察者 （Observable） / 观察者（Observer） 的工作线程类型
     * 参考链接：https://www.jianshu.com/p/5225b2baaecd
     */
    private void functionMethod() {
        mShowMessage.setText("详细内容，请查看日志，通过‘RxJava’过滤");
        // 步骤1：创建被观察者 Observable & 发送事件
        // 在主线程创建被观察者 Observable 对象
        // 所以生产事件的线程是：主线程
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {

                Log.d(TAG, " 被观察者 Observable的工作线程是: " + Thread.currentThread().getName());
                // 打印验证
                emitter.onNext(1);
                emitter.onComplete();
            }
        });
// 步骤2：创建观察者 Observer 并 定义响应事件行为
        // 在主线程创建观察者 Observer 对象
        // 所以接收 & 响应事件的线程是：主线程
        Observer<Integer> observer = new Observer<Integer>() {

            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "开始采用subscribe连接");
                Log.d(TAG, " 观察者 Observer的工作线程是: " + Thread.currentThread().getName());
                // 打印验证

            }

            @Override
            public void onNext(Integer value) {
                Log.d(TAG, "对Next事件" + value + "作出响应");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "对Error事件作出响应");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "对Complete事件作出响应");
            }
        };

// 步骤3：通过订阅（subscribe）连接观察者和被观察者
        observable.subscribe(observer);
        /**
         * 特别注意

         1. 若Observable.subscribeOn（）多次指定被观察者 生产事件的线程，则只有第一次指定有效，其余的指定线程无效
         *
         *2. 若Observable.observeOn（）多次指定观察者 接收 & 响应事件的线程，则每次指定均有效，即每指定一次，就会进行一次线程的切换
         */

    }
}
