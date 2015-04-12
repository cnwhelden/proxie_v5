package rem.proxie.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;

import rem.proxie.R;
import rem.proxie.activities.Login;
import rem.proxie.activities.MessageForum;

public class Start extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PreferenceManager.setDefaultValues(this, R.xml.app_preferences, false);
        PreferenceManager.setDefaultValues(this, R.xml.map_preferences, false);
        PreferenceManager.setDefaultValues(this, R.xml.message_preferences, false);

        startWifiP2p();

        if (getIntent().hasExtra("loginSuccessful")) {
            Intent i = new Intent(this, MessageForum.class);
            startActivity(i);
            finish();
            return;
        }

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        String stayLoggedInKey = getString(R.string.appPreferencesStayLoggedInKey);
        Boolean stayLoggedInValue = sharedPref.getBoolean(stayLoggedInKey, false);

        boolean newUser = true;

        if(stayLoggedInValue.booleanValue() == true) {

            String usernameKey = getString(R.string.usernameKey);
            String noPrefValue = getString(R.string.noPrefValue);
            String savedUsername = sharedPref.getString(usernameKey, noPrefValue);

            if (!savedUsername.equalsIgnoreCase(noPrefValue)) {
                Toast.makeText(getApplicationContext(), "User already logged in", Toast.LENGTH_LONG).show();
                newUser = false;
            }
        }

        if (newUser) {
                Intent i = new Intent(this, Login.class);
                startActivity(i);
                finish();
        }
        else {
            Intent i = new Intent(this, MessageForum.class);
            startActivity(i);
            finish();
        }

    }

    public void startWifiP2p() {
        WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        wifiManager.setWifiEnabled(true);
    }

}
