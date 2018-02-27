package scut.carson_ho.rxjava_operators.rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import scut.carson_ho.rxjava_operators.R;

/**
 * Created by JayHan on 2018/2/11. 功能防抖
 * 即:用户在规定时间内多次触发该功能，仅会响应第一次触发操作
 */

public class AntiShakeActivity extends AppCompatActivity {
    private Button mAnti_shake;
    private static final String TAG = "RxJava";

    /**
     * 功能防抖 实例
     * 参考链接：https://www.jianshu.com/p/90d53c791c42
     * 场景：即用户在规定时间内多次触发该功能，仅会响应第一次触发操作
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anti_shake);

        mAnti_shake = (Button) findViewById(R.id.anti_shake);

        /*
         * 1. 此处采用了RxBinding：RxView.clicks(button) = 对控件点击进行监听，需要引入依赖：compile 'com.jakewharton.rxbinding2:rxbinding:2.0.0'
         * 2. 传入Button控件，点击时，都会发送数据事件（但由于使用了throttleFirst（）操作符，所以只会发送该段时间内的第1次点击事件）
         **/
        RxView.clicks(mAnti_shake)
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object o) {
                        Log.d(TAG, "发送了网络请求");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应" + e.toString());
                        // 获取异常错误信息
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }
                });

    }
}
