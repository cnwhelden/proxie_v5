package rem.proxie.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import rem.proxie.R;
import rem.proxie.preferences.MapSettings;
import rem.proxie.map_util.LocationServer;

public class Map extends Activity {

    private LocationServer locServer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);

        locServer = new LocationServer(this);

    }

    @Override
    protected void onResume() {
        super.onResume();

        locServer.start();

        String key = getString(R.string.mapPreferencesDisplayMessageLocationKey);
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        Boolean displayMarkers = sharedPref.getBoolean(key, true);
        String text = displayMarkers.toString();

        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();

        locServer.stop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_map, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.mapPreferencesMenuItem) {
            Intent i = new Intent(this, MapSettings.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }
}
