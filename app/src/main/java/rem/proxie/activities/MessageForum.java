package rem.proxie.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import rem.proxie.R;
import rem.proxie.message_service.Message;
import rem.proxie.message_service.MessageService;
import rem.proxie.message_service.MessageListAdapter;
import rem.proxie.preferences.AppSettings;

public class MessageForum extends Activity {

    static final String TAG = "Proxie";
    static final int COMPOSE_MESSAGE_REQUEST = 0;
    static final String EXTRA_KEY_MESSAGE = "message";
    static final String EXTRA_KEY_MESSAGE_POSITION = "position";

    static ArrayList<Message> messages = null;
    private MessageListAdapter adapter = null;
    private MessageService messageService = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_forum);

        messages = new ArrayList<Message>();

        ListView listView = (ListView) findViewById(R.id.messageForumListView);
        adapter = new MessageListAdapter(this,
                android.R.layout.simple_list_item_1, messages);
        listView.setAdapter(adapter);

        AdapterView.OnItemClickListener messageClickedHandler = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {

                Intent i = new Intent(parent.getContext(), MessageDialog.class);
                i.putExtra(MessageForum.EXTRA_KEY_MESSAGE_POSITION, position);
                startActivity(i);
            }
        };
        listView.setOnItemClickListener(messageClickedHandler);

        messageService = new MessageService(this, messages, adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_message_forum, menu);
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
            case R.id.appPreferencesMenuItem:
                i = new Intent(this, AppSettings.class);
                startActivity(i);
                break;
            case R.id.contactsMenuItem:
                i = new Intent(this, Contacts.class);
                startActivity(i);
                break;
            case R.id.mapMenuItem:
                i = new Intent(this, Map.class);
                startActivity(i);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void compose_message(View v) {
        Intent i = new Intent(this, ComposeMessage.class);
        startActivityForResult(i, COMPOSE_MESSAGE_REQUEST);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == COMPOSE_MESSAGE_REQUEST) {
            if (resultCode == RESULT_OK) {
                /* TODO: Get the message from the intent*/
                Message m = data.getParcelableExtra(EXTRA_KEY_MESSAGE);
                messageService.addMessage(m);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /* unregister the broadcast receiver */
    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onStop();
    }


}
