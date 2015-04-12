package rem.proxie.message_service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.util.ArrayList;
import java.util.Timer;

import rem.proxie.activities.MessageForum;

public class MessageService extends Service {

    private MessageForum activity;
    private MessageListAdapter adapter;
    private ArrayList<Message> messages;

    public MessageService(MessageForum activity, ArrayList<Message> messages, MessageListAdapter adapter) {
        this.activity = activity;
        this.messages = messages;
        this.adapter = adapter;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void addMessage(Message message) {
        long expirationTime = message.getExpirationTime();

        if (expirationTime <= System.currentTimeMillis())
            return;

        messages.add(message);
        adapter.notifyDataSetChanged();

        Timer timer = new Timer();
        RemoveMessageTask task = new RemoveMessageTask(this, message);
        long milliseconds = expirationTime - System.currentTimeMillis();
        timer.schedule(task, milliseconds);
    }

    public void removeMessage(Message message) {
        int index = messages.indexOf(message);
        messages.remove(index);
        adapter.notifyDataSetChanged();
    }
}
