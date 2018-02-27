package scut.carson_ho.rxjava_operators.rxjava;

import android.content.Intent;
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
import io.reactivex.functions.Consumer;
import scut.carson_ho.rxjava_operators.R;

/**
 * Created by JayHan on 2018/2/8. 组合/合并操作符
 */

public class MergeActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "RxJava";

    private Button mMergeAndShow;
    private Button mUniteAndJudge;
    private Button mObtainData;

    private TextView mShowMessage;
    String result = "数据源来自 = ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merge);

        mMergeAndShow = (Button) findViewById(R.id.mergeAndShow);
        mUniteAndJudge = (Button) findViewById(R.id.uniteAndJudge);
        mObtainData = (Button) findViewById(R.id.obtainData);

        mShowMessage = (TextView) findViewById(R.id.showMessage);

        mMergeAndShow.setOnClickListener(this);
        mUniteAndJudge.setOnClickListener(this);
        mObtainData.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mergeAndShow:
                mergeAndShowMethod();
                break;
            case R.id.uniteAndJudge:
                uniteAndJudgeMethod();
                break;
            case R.id.obtainData:
                obtainDataMethod();
                break;
        }
    }


    /**
     * 从磁盘/内存缓存中 获取缓存数据实例
     * 如 需要向服务器获取数据
     * 参考链接：https://www.jianshu.com/p/6f3b6b934787
     */
    private void obtainDataMethod() {
        mShowMessage.setText("详细内容，请查看日志，通过‘RxJava’过滤");
// 该2变量用于模拟内存缓存 & 磁盘缓存中的数据
        final String memoryCache = null;
        final String diskCache = "从磁盘缓存中获取数据";

        /*
         * 设置第1个Observable：检查内存缓存是否有该数据的缓存
         **/
        Observable<String> memory = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {

                // 先判断内存缓存有无数据
                if (memoryCache != null) {
                    // 若有该数据，则发送
                    emitter.onNext(memoryCache);
                } else {
                    // 若无该数据，则直接发送结束事件
                    emitter.onComplete();
                }

            }
        });

        /*
         * 设置第2个Observable：检查磁盘缓存是否有该数据的缓存
         **/
        Observable<String> disk = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {

                // 先判断磁盘缓存有无数据
                if (diskCache != null) {
                    // 若有该数据，则发送
                    emitter.onNext(diskCache);
                } else {
                    // 若无该数据，则直接发送结束事件
                    emitter.onComplete();
                }

            }
        });

        /*
         * 设置第3个Observable：通过网络获取数据
         **/
        Observable<String> network = Observable.just("从网络中获取数据");
        // 此处仅作网络请求的模拟

/*
         * 通过concat（） 和 firstElement（）操作符实现缓存功能
         **/

        // 1. 通过concat（）合并memory、disk、network 3个被观察者的事件（即检查内存缓存、磁盘缓存 & 发送网络请求）
        //    并将它们按顺序串联成队列
        Observable.concat(memory, disk, network)
                // 2. 通过firstElement()，从串联队列中取出并发送第1个有效事件（Next事件），即依次判断检查memory、disk、network
                .firstElement()
                // 即本例的逻辑为：
                // a. firstElement()取出第1个事件 = memory，即先判断内存缓存中有无数据缓存；由于memoryCache = null，即内存缓存中无数据，所以发送结束事件（视为无效事件）
                // b. firstElement()继续取出第2个事件 = disk，即判断磁盘缓存中有无数据缓存：由于diskCache ≠ null，即磁盘缓存中有数据，所以发送Next事件（有效事件）
                // c. 即firstElement()已发出第1个有效事件（disk事件），所以停止判断。

                // 3. 观察者订阅
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.d(TAG, "最终获取的数据来源 =  " + s);
                    }
                });
    }


    /**
     * 需要同时对多个事件进行联合判断 实例
     * 如 填写表单时，需要表单里所有信息（姓名、年龄、职业等）都被填写后，才允许点击 "提交" 按钮
     * 参考链接：https://www.jianshu.com/p/2becc0eaedab
     */
    private void uniteAndJudgeMethod() {
        mShowMessage.setText("详细内容，请查看日志，通过‘RxJava’过滤");
        startActivity(new Intent(this, UniteAndJudgeActivity.class));
    }


    /**
     * 合并数据源&同时展示实例
     * 如 同时向2个数据源获取数据 -> 合并数据 -> 统一展示到客户端
     * 参考链接：https://www.jianshu.com/p/fc2e551b907c
     */
    private void mergeAndShowMethod() {
        mShowMessage.setText("详细内容，请查看日志，通过‘RxJava’过滤");

        //实现思路：采用 Merge（）操作符
        /*
         * 设置第1个Observable：通过网络获取数据
         * 此处仅作网络请求的模拟
         **/
        Observable<String> network = Observable.just("网络");

        /*
         * 设置第2个Observable：通过本地文件获取数据
         * 此处仅作本地文件请求的模拟
         **/
        Observable<String> file = Observable.just("本地文件");
       /*
         * 通过merge（）合并事件 & 同时发送事件
         **/
        Observable.merge(network, file).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String value) {
                Log.d(TAG, "数据源有： " + value);
                result += value + "+";
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "对Error事件作出响应");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "获取数据完成");
                Log.d(TAG, result);
            }
        });
    }
}
