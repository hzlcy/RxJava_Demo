package scut.carson_ho;

import java.util.Date;
import java.util.UUID;

/**
 * Created by JayHan on 2018/2/13.
 */

public class Crime {
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;

    public Crime() {
        mId = UUID.randomUUID();
        mDate = new Date();
    }

    public UUID getmId() {
        return mId;
    }
}
