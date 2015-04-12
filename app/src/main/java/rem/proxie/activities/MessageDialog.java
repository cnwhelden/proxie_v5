package rem.proxie.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import java.text.SimpleDateFormat;
import java.util.Date;

import rem.proxie.R;
import rem.proxie.message_service.Message;

public class MessageDialog extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_dialog);

        int position = getIntent().getIntExtra(MessageForum.EXTRA_KEY_MESSAGE_POSITION, -1);
        Message m = MessageForum.messages.get(position);

        TextView view = (TextView) findViewById(R.id.messageDialogText);
        view.setText("Text: " + m.getText());

        view = (TextView) findViewById(R.id.messageDialogSender);
        view.setText("Sender: " + m.getSender());

        view  = (TextView) findViewById(R.id.messageDialogTarget);
        view.setText("Target: " + m.getTarget());

        view  = (TextView) findViewById(R.id.messageDialogLocation);
        view.setText("Source Location: " + m.getSendersLocation().toString());

        view  = (TextView) findViewById(R.id.messageDialogNumHops);
        view.setText("Hops Remaining: " + m.getNumHops());

        view  = (TextView) findViewById(R.id.messageDialogExpirationTime);
        String date = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss")
                .format(new Date(m.getExpirationTime()));
        view.setText("Expiration Date: " + date);

        view = (TextView) findViewById(R.id.messageDialogCreatedByUser);
        if (m.getCreatedByUser() == Message.TRUE)
            view.setText("Created by user: true");
        else
            view.setText("Created by user: false");

    }

}
