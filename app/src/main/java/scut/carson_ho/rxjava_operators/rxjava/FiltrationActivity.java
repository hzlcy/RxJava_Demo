package scut.carson_ho.rxjava_operators.rxjava;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import scut.carson_ho.rxjava_operators.R;

/**
 * Created by JayHan on 2018/2/8. 过滤操作符
 */

public class FiltrationActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mSearchOptimize;
    private Button mAnti_shake;

    private TextView mShowMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtration);

        mSearchOptimize = (Button) findViewById(R.id.searchOptimize);
        mAnti_shake = (Button) findViewById(R.id.anti_shake);
        mShowMessage = (TextView) findViewById(R.id.showMessage);

        mSearchOptimize.setOnClickListener(this);
        mAnti_shake.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.searchOptimize:
                searchOptimizeMethod();
                break;
            case R.id.anti_shake:
                antiShakeMethod();
                break;
        }
    }

    /**
     * 功能防抖 实例
     * 参考链接：https://www.jianshu.com/p/90d53c791c42
     * 场景：即用户在规定时间内多次触发该功能，仅会响应第一次触发操作
     */
    private void antiShakeMethod() {
        mShowMessage.setText("详细内容，请查看日志，通过‘RxJava’过滤");
        startActivity(new Intent(this, AntiShakeActivity.class));
    }

    /**
     * 联想搜索请求优化 实例
     * 参考链接：https://www.jianshu.com/p/ba0e7df9b927
     * 即每当用户输入1个字符，显示与当前输入框内字符相关的搜索结果
     */
    private void searchOptimizeMethod() {
        mShowMessage.setText("详细内容，请查看日志，通过‘RxJava’过滤");
        startActivity(new Intent(this, SearchActivity.class));
    }
}
