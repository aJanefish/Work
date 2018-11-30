package zy.walk.com.thewalkers.preference;

import android.preference.PreferenceFragment;

import zy.walk.com.thewalkers.R;

public class DemoPreferenceFragment extends PreferenceFragment {

    @Override
    public void onStart() {
        super.onStart();
        addPreferencesFromResource(R.xml.demo_preferences);
    }
}
