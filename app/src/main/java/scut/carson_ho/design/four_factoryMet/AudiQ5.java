package scut.carson_ho.design.four_factoryMet;

import android.util.Log;

/**
 * Created by JayHan on 2018/6/11.
 */

public class AudiQ5 extends AudiCar{
    @Override
    public void drive() {
        Log.i("hzl_message", "Q5 drive----");
    }

    @Override
    public void selfNavigation() {
        Log.i("hzl_message", "Q5 selfNavigation----");
    }
}
