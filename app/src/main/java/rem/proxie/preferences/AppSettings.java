package rem.proxie.preferences;

import android.app.Activity;
import android.os.Bundle;

public class AppSettings extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new AppSettingsFragment())
                .commit();
    }

}
