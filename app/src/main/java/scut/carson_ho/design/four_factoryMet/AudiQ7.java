package scut.carson_ho.design.four_factoryMet;

import android.util.Log;

/**
 * Created by JayHan on 2018/6/11.
 */

public class AudiQ7 extends AudiCar{
    @Override
    public void drive() {
        Log.i("hzl_message", "Q7 drive----");
    }

    @Override
    public void selfNavigation() {
        Log.i("hzl_message", "Q7 selfNavigation----");
    }
}
