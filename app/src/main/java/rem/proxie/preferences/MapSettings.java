package rem.proxie.preferences;

import android.app.Activity;
import android.os.Bundle;

public class MapSettings extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new MapSettingsFragment())
                .commit();
    }

}
