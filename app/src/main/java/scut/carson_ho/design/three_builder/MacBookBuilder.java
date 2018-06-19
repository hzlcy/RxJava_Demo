package scut.carson_ho.design.three_builder;

/**
 * Created by JayHan on 2018/6/8.
 */

public class MacBookBuilder extends Builder {
    private Computer mComputer = new Macbook();

    @Override
    public void builderBoard(String board) {
        mComputer.setmBoard(board);
    }

    @Override
    public void builderDisplay(String display) {
        mComputer.setmDiaplay(display);
    }

    @Override
    public void builderOS() {
        mComputer.setOS();
    }

    @Override
    public Computer creat() {
        return mComputer;
    }
}
