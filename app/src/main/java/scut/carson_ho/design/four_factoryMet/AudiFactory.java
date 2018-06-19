package scut.carson_ho.design.four_factoryMet;

/**
 * Created by JayHan on 2018/6/11.
 */

public abstract class AudiFactory {
    public abstract <T extends AudiCar> T createAudiCar(Class<T> clz);
}
