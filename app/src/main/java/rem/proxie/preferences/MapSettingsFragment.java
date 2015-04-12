package rem.proxie.preferences;


import android.os.Bundle;
import android.preference.PreferenceFragment;

import rem.proxie.R;

public class MapSettingsFragment extends PreferenceFragment {


    public MapSettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.map_preferences);
    }




}
