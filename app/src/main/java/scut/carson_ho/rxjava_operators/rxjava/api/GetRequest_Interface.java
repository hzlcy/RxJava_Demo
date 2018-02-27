package scut.carson_ho.rxjava_operators.rxjava.api;

import io.reactivex.Observable;
import retrofit2.http.GET;
import scut.carson_ho.rxjava_operators.rxjava.bean.Translation1;
import scut.carson_ho.rxjava_operators.rxjava.bean.Translation2;
import scut.carson_ho.rxjava_operators.rxjava.bean.Translation;

/**
 * Created by Carson_Ho on 17/9/8.
 */

public interface GetRequest_Interface {

        @GET("ajax.php?a=fy&f=auto&t=auto&w=hi%20world")
        Observable<Translation> getCall();
        // 注解里传入 网络请求 的部分URL地址
        // Retrofit把网络请求的URL分成了两部分：一部分放在Retrofit对象里，另一部分放在网络请求接口里
        // 如果接口里的url是一个完整的网址，那么放在Retrofit对象里的URL可以忽略
        // 采用Observable<...>接口
        // getCall()是接受网络请求数据的方法

    // 网络请求1
    @GET("ajax.php?a=fy&f=auto&t=auto&w=hi%20register")
    Observable<Translation1> getCall_1();

    // 网络请求2
    @GET("ajax.php?a=fy&f=auto&t=auto&w=hi%20login")
    Observable<Translation2> getCall_2();


    }

