package com.example.anastasia.memorycards;

import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;

/**
 * Created by Anastasia on 04.07.2016.
 */
public class Settings extends PreferenceActivity implements Preference.OnPreferenceChangeListener {
    ListPreference color;
   // ListPreference picture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
        color=(ListPreference)this.findPreference("BackgroundColor");
        color.setOnPreferenceChangeListener(this);
        color.setSummary(color.getEntry());


    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        preference.setSummary((CharSequence)newValue);
        return true;
    }
}
