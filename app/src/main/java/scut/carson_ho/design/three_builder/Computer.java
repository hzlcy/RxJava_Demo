package scut.carson_ho.design.three_builder;

/**
 * Created by JayHan on 2018/6/7.
 */

//计算机抽象类，即product产品类，抽象类
public abstract class Computer {
    protected String mBoard;
    protected String mDisplay;
    protected String mOS;

    protected Computer() {
    }

    //设置主板
    public void setmBoard(String mBoard) {
        this.mBoard = mBoard;
    }

    //设置显示器
    public void setmDiaplay(String mDisplay) {
        this.mDisplay = mDisplay;
    }

    //设置操作系统
    public abstract void setOS();

    @Override
    public String toString() {
        return "Computer [mBoard=" + mBoard + ",mDisplay=" + mDisplay + ",mOS=" + mOS + "]";
    }
}
