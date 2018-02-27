package scut.carson_ho.rxjava_operators.rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import scut.carson_ho.rxjava_operators.R;

/**
 * Created by JayHan on 2018/2/11. 联想搜索请求优化
 * 即每当用户输入1个字符，显示与当前输入框内字符相关的搜索结果
 * 参考链接：https://www.jianshu.com/p/ba0e7df9b927
 */

public class SearchActivity extends AppCompatActivity {
    private static final String TAG = "RxJava";
    private EditText ed;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        /*
         * 说明
         * 1. 此处采用了RxBinding：RxTextView.textChanges(name) = 对对控件数据变更进行监听（功能类似TextWatcher），需要引入依赖：compile 'com.jakewharton.rxbinding2:rxbinding:2.0.0'
         * 2. 传入EditText控件，输入字符时都会发送数据事件（此处不会马上发送，因为使用了debounce（））
         * 3. 采用skip(1)原因：跳过 第1次请求 = 初始输入框的空字符状态
         **/

        RxTextView.textChanges(ed)
                .debounce(1, TimeUnit.SECONDS)
                .skip(1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CharSequence>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CharSequence charSequence) {
                        tv.setText("发送给服务器的字符 = " + charSequence.toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }
                });

    }
}
