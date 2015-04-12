package rem.proxie.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.maps.model.LatLng;

import rem.proxie.R;
import rem.proxie.message_service.Message;
import rem.proxie.preferences.MessageSettings;


public class ComposeMessage extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compose_message);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_compose_message, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Intent i;

        switch (id) {
            case R.id.messagePreferencesMenuItem:
                i = new Intent(this, MessageSettings.class);
                startActivity(i);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void send_message(View v) {

        EditText toEditText = (EditText) findViewById(R.id.composeMessageToEditText);
        EditText messageEditText = (EditText) findViewById(R.id.composeMessageMessageEditText);

        String to = toEditText.getText().toString();
        to = (to.length() > 0) ? to : Message.BROADCAST_MESSAGE;
        String mssg = messageEditText.getText().toString();

        Message m = new Message(mssg, Message.ANONYMOUS, to,
                new LatLng(0, 0), 1, System.currentTimeMillis() + (60000), Message.TRUE);

        Intent i = new Intent();
        i.putExtra(MessageForum.EXTRA_KEY_MESSAGE, m);
        this.setResult(RESULT_OK, i);

        finish();
    }

    public void cancel(View v) {

        finish();
    }

}
