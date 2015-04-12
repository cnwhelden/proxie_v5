package rem.proxie.activities;


import android.app.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import rem.proxie.R;

public class Login extends Activity {

    private String savedUsername;
    private String savedPassword;
    private boolean newUser = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        String usernameKey = getString(R.string.usernameKey);
        String passwordKey = getString(R.string.passwordKey);
        String noPrefValue = getString(R.string.noPrefValue);

        savedUsername = sharedPref.getString(usernameKey, noPrefValue);
        savedPassword = sharedPref.getString(passwordKey, noPrefValue);

        if (savedUsername.equalsIgnoreCase(noPrefValue)) {
            Toast.makeText(getApplicationContext(), "New user", Toast.LENGTH_LONG).show();
            newUser = true;
        }

        EditText editText = (EditText) findViewById(R.id.loginPasswordEditText);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                attemptLogin(v);
                return true;
            }
        });
    }

    public void attemptLogin(View view) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        EditText usernameView = (EditText) findViewById(R.id.loginUsernameEditText);
        EditText passwordView = (EditText) findViewById(R.id.loginPasswordEditText);

        // Reset errors.
        usernameView.setError(null);
        passwordView.setError(null);

        // Store values at the time of the login attempt.
        String username = usernameView.getText().toString();
        String password = passwordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password.
        if (TextUtils.isEmpty(password)) {
            passwordView.setError(getString(R.string.error_field_required));
            focusView = passwordView;
            cancel = true;
        }
        else if (!isPasswordValid(password)) {
            passwordView.setError(getString(R.string.error_invalid_password));
            focusView = passwordView;
            cancel = true;
        }
        else if (!newUser && !savedPassword.equalsIgnoreCase(password)) {
            usernameView.setError(getString(R.string.error_incorrect_username_or_password));
            focusView = usernameView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(username)) {
            usernameView.setError(getString(R.string.error_field_required));
            focusView = usernameView;
            cancel = true;
        }
        else if (!isUserNameValid(username)) {
            usernameView.setError(getString(R.string.error_invalid_username));
            focusView = usernameView;
            cancel = true;
        }
        else if (!newUser && !savedUsername.equalsIgnoreCase(username)) {
            usernameView.setError(getString(R.string.error_incorrect_username_or_password));
            focusView = usernameView;
            cancel = true;
        }


        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
            return;
        }
        else if (newUser) {

            String usernameKey = getString(R.string.usernameKey);
            String passwordKey = getString(R.string.passwordKey);

            sharedPref.edit().putString(usernameKey, username).apply();
            sharedPref.edit().putString(passwordKey, password).apply();
        }

        CheckBox checkbox = (CheckBox) findViewById(R.id.loginStayLoggedInCheckBox);
        if (checkbox.isChecked()) {
            Toast.makeText(getApplicationContext(), "Is Checked", Toast.LENGTH_LONG).show();
            String key = getString(R.string.appPreferencesStayLoggedInKey);
            sharedPref.edit().putBoolean(key, true).apply();
        }

        // Valid username and password given.  Load Start Activity.
        Intent i = new Intent(this, Start.class);
        i.putExtra("loginSuccessful", "true");
        startActivity(i);
        finish();
    }

    private boolean isUserNameValid(String username) {
            return username.length() > 3;
    }

    private boolean isPasswordValid(String password) {
            return password.length() > 3;
    }


}



