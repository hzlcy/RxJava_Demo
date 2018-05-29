package scut.carson_ho.rxjava_operators.rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import scut.carson_ho.rxjava_operators.rxjava.api.GetRequest_Interface;
import scut.carson_ho.rxjava_operators.R;
import scut.carson_ho.rxjava_operators.rxjava.bean.Translation1;
import scut.carson_ho.rxjava_operators.rxjava.bean.Translation2;

/**
 * Created by JayHan on 2018/2/8. 变换操作符
 * 使用场景：进行嵌套网络请求
 * 即在第1个网络请求成功后，继续再进行一次网络请求
 */

public class ChangeActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "RxJava";
    private Button mBtn;

    private TextView mShowMessage;

    // 定义Observable接口类型的网络请求对象
    Observable<Translation1> observable1;
    Observable<Translation2> observable2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);

        mBtn = (Button) findViewById(R.id.loginAndRegister);
        mShowMessage = (TextView) findViewById(R.id.showMessage);

        mBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.loginAndRegister:
                queryMethod();
                break;
        }
    }

    /**
     * 嵌套网络请求实例
     * 如 先进行 用户注册 的网络请求, 待注册成功后回再继续发送 用户登录 的网络请求
     * 参考链接：https://www.jianshu.com/p/5f5d61f04f96
     */
    private void queryMethod() {
        mShowMessage.setText("详细内容，请查看日志，通过‘RxJava’过滤");

// 步骤1：创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fy.iciba.com/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 支持RxJava
                .build();
// 步骤2：创建 网络请求接口 的实例
        GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);

        // 步骤3：采用Observable<...>形式 对 2个网络请求 进行封装
        observable1 = request.getCall_1();
        observable2 = request.getCall_2();
        observable1.subscribeOn(Schedulers.io())// （初始被观察者）切换到IO线程进行网络请求1
                .observeOn(AndroidSchedulers.mainThread()) // （新观察者）切换到主线程 处理网络请求1的结果
                .doOnNext(new Consumer<Translation1>() {
                    @Override
                    public void accept(@NonNull Translation1 result) throws Exception {
                        Log.d(TAG, "第1次网络请求成功");
                        result.show();
                        // 对第1次网络请求返回的结果进行操作 = 显示翻译结果
                    }
                }).observeOn(Schedulers.io())// （新被观察者，同时也是新观察者）切换到IO线程去发起登录请求

                // 特别注意：因为flatMap是对初始被观察者作变换，所以对于旧被观察者，它是新观察者，所以通过observeOn切换线程
                // 但对于初始观察者，它则是新的被观察者

                .flatMap(new Function<Translation1, ObservableSource<Translation2>>() {
                    // 作变换，即作嵌套网络请求
                    @Override
                    public ObservableSource<Translation2> apply(@NonNull Translation1 translation1) throws Exception {
                        if (1 > 0) {
                            return null;
                        }
                        // 将网络请求1转换成网络请求2，即发送网络请求2
                        return observable2;
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                // （初始观察者）切换到主线程 处理网络请求2的结果
                .subscribe(new Consumer<Translation2>() {
                    @Override
                    public void accept(@NonNull Translation2 result) throws Exception {
                        Log.d(TAG, "第2次网络请求成功");
                        result.show();
                        // 对第2次网络请求返回的结果进行操作 = 显示翻译结果
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.d(TAG, "登录失败");
                    }
                });


    }
}
