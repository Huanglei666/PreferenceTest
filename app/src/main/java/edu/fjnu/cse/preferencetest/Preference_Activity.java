package edu.fjnu.cse.preferencetest;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class Preference_Activity  extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}