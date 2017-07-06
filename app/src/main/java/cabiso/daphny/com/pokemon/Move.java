package cabiso.daphny.com.pokemon;

/**
 * Created by Lenovo on 7/6/2017.
 */

public class Move {

    private String mName;
    private String mAccuracy;
    private String mPower;

    public void setmName(String mName) {
        this.mName = mName;
    }

    public void setmAccuracy(String mAccuracy) {
        this.mAccuracy = mAccuracy;
    }

    public void setmPower(String mPower) {
        this.mPower = mPower;
    }

    public String getmName() {
        return mName;
    }

    public String getmAccuracy() {
        return mAccuracy;
    }

    public String getmPower() {
        return mPower;
    }
}
