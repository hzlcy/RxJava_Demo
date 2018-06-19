package scut.carson_ho.design.four_factoryMet;

import java.util.ArrayList;

/**
 * Created by JayHan on 2018/6/11.
 */

public class AudiCarFactory extends AudiFactory {


    @Override
    public <T extends AudiCar> T createAudiCar(Class<T> clz) {
        AudiCar car = null;
        try {
            car = (AudiCar) Class.forName(clz.getName()).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) car;
    }
}
