package rem.proxie.preferences;


import android.os.Bundle;
import android.preference.PreferenceFragment;

import rem.proxie.R;


public class AppSettingsFragment extends PreferenceFragment {


    public AppSettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.app_preferences);
    }


}
