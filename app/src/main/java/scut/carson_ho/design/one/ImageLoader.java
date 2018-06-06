package scut.carson_ho.design.one;


import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.LogRecord;

/**
 * Created by JayHan on 2018/6/5.
 */

public class ImageLoader {
    //图片缓存
    ImageLoader imageLoader = new ImageLoader();
    //线程池，线程池数量为CPU的数量
    ExecutorService mExecutorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    //UI Handler
    Handler handler = new Handler(Looper.getMainLooper());
}
