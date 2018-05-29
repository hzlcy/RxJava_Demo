package scut.carson_ho.rxjava_operators;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import scut.carson_ho.Crime;
import scut.carson_ho.rxjava_operators.rxjava.ChangeActivity;
import scut.carson_ho.rxjava_operators.rxjava.CreateActivity;
import scut.carson_ho.rxjava_operators.rxjava.FiltrationActivity;
import scut.carson_ho.rxjava_operators.rxjava.FunctionActivity;
import scut.carson_ho.rxjava_operators.rxjava.MergeActivity;

/**
 * Created by JayHan on 2018/2/8. RxJava实际应用教学
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mCreate;
    private Button mChange;
    private Button mMerge;
    private Button mFunction;
    private Button mFiltration;
    private Button mGlide;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCreate = (Button) findViewById(R.id.create);
        mChange = (Button) findViewById(R.id.change);
        mMerge = (Button) findViewById(R.id.merge);
        mFunction = (Button) findViewById(R.id.function);
        mFiltration = (Button) findViewById(R.id.filtration);
        mGlide = (Button) findViewById(R.id.glide);

        mCreate.setOnClickListener(this);
        mChange.setOnClickListener(this);
        mMerge.setOnClickListener(this);
        mFunction.setOnClickListener(this);
        mFiltration.setOnClickListener(this);
        mGlide.setOnClickListener(this);

        Toast.makeText(this, new Crime().getmId() + "", Toast.LENGTH_LONG).show();



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.create:
                //创建操作符
                // 使用场景：客户端隔固定时间主动向服务器发送请求获取信息
                startActivity(new Intent(this, CreateActivity.class));
                break;
            case R.id.change:
                //变换操作符
                // 使用场景：进行嵌套网络请求
                startActivity(new Intent(this, ChangeActivity.class));
                break;
            case R.id.merge:
                //组合/合并操作符
                // 使用场景：1.从磁盘/内存缓存中 获取缓存数据实例
                //          2.需要同时对多个事件进行联合判断
                //          3.合并数据源&同时展示
                startActivity(new Intent(this, MergeActivity.class));
                break;
            case R.id.function:
                //功能性操作符
                // 使用场景：线程控制（也称为调度 / 切换）
                startActivity(new Intent(this, FunctionActivity.class));
                break;
            case R.id.filtration:
                //过滤操作符
                // 使用场景：1.即用户在规定时间内多次触发该功能，仅会响应第一次触发操作
                //          2.每当用户输入1个字符，显示与当前输入框内字符相关的搜索结果
                startActivity(new Intent(this, FiltrationActivity.class));

                break;
            case R.id.glide:


                break;
        }
    }
}




