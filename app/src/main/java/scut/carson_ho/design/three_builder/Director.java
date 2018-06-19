package scut.carson_ho.design.three_builder;

/**
 * Created by JayHan on 2018/6/8.
 */

public class Director {
    Builder mBuilder = null;

    public Director(Builder mBuilder) {
        this.mBuilder = mBuilder;
    }

    public void construct(String board, String display) {
        mBuilder.builderBoard(board);
        mBuilder.builderDisplay(display);
        mBuilder.builderOS();
    }
}
