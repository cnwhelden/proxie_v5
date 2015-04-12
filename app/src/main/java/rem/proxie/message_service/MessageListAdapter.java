package rem.proxie.message_service;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

import rem.proxie.R;

public class MessageListAdapter extends ArrayAdapter<Message> {

    private Context context;
    private int resource;
    private ArrayList<Message> messages;

    public MessageListAdapter(Context context, int resource, ArrayList<Message> messages) {
        super(context, resource, messages);
        this.context = context;
        this.resource = resource;
        this.messages = messages;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null){
            LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = vi.inflate(R.layout.message_list, null);
        }

        Message message = messages.get(position);

        if (message != null){
            TextView source = (TextView) view.findViewById(R.id.messageForumListSource);
            source.setText("(" + message.getSender() + ") ");

            TextView text = (TextView) view.findViewById(R.id.messageForumListText);
            text.setText(message.getText());
        }
        return view;
    }
}
