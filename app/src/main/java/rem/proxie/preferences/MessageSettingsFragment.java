package rem.proxie.preferences;


import android.os.Bundle;
import android.preference.PreferenceFragment;

import rem.proxie.R;

public class MessageSettingsFragment extends PreferenceFragment {

    public MessageSettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.message_preferences);
    }


}
