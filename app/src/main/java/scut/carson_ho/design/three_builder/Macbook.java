package scut.carson_ho.design.three_builder;

/**
 * Created by JayHan on 2018/6/8.
 */
//具体的Computer类
public class Macbook extends Computer {
    protected Macbook() {
    }

    @Override
    public void setOS() {
        mOS = "Mac OS X 10.10";
    }
}
