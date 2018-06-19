package scut.carson_ho.design;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import scut.carson_ho.design.four_factoryMet.AudiCarFactory;
import scut.carson_ho.design.four_factoryMet.AudiFactory;
import scut.carson_ho.design.four_factoryMet.AudiQ3;
import scut.carson_ho.design.three_builder.Builder;
import scut.carson_ho.design.three_builder.Director;
import scut.carson_ho.design.three_builder.MacBookBuilder;
import scut.carson_ho.rxjava_operators.R;


/**
 * Created by JayHan on 2018/6/6.
 */

public class MainActivity4 extends AppCompatActivity {

    private static final String TAG = "hzl_message";

    public List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Log.i(TAG, "DCL:" + Singleton.getInstance());
//        Log.i(TAG, "DCL:" + Singleton.getInstance());
//        Singleton_03.SINGLETON.doSomeThing();
//        Log.i(TAG, "枚举:"+Singleton_03.SINGLETON);
//        Log.i(TAG, "枚举:" + Singleton_03.SINGLETON);

//        Builder builder = new MacBookBuilder();
//        Director director = new Director(builder);
//        director.construct("英特尔主板", "Acer显示器");
//        builder.creat().toString();
//
//        showDialog(this);

        AudiFactory audiFactory = new AudiCarFactory();
        AudiQ3 audiQ3 = audiFactory.createAudiCar(AudiQ3.class);
        audiQ3.drive();
        audiQ3.selfNavigation();


    }

    private void showDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle("Title");
        builder.setMessage("message");
        builder.setPositiveButton("Button1", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                setTitle("点击了Button1");
            }
        });

        builder.setNeutralButton("Button2", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                setTitle("点击了Button2");
            }
        });

        builder.setNegativeButton("Button3", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                setTitle("点击了Button3");
            }
        });
        builder.create().show();
    }
}
