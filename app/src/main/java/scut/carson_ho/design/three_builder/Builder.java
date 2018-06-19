package scut.carson_ho.design.three_builder;

/**
 * Created by JayHan on 2018/6/8.
 */
//抽象builder类
public abstract class Builder {
    //设置主机
    public abstract void builderBoard(String board);
    //设置显示器
    public abstract void builderDisplay(String display);
    //设置操作系统
    public abstract void builderOS();
    //创建Computer
    public abstract Computer creat();
}
