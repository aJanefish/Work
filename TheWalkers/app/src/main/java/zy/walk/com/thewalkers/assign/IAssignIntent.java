package zy.walk.com.thewalkers.assign;

import android.app.Activity;

public interface IAssignIntent {
    LaunchIntentPraseData getLaunchIntentParseData();

    Class target();

    void start();
}
