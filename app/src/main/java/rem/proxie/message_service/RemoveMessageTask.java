package rem.proxie.message_service;

import android.os.Handler;
import java.util.TimerTask;

public class RemoveMessageTask extends TimerTask {

    private MessageService service = null;
    private Message message = null;
    private Handler handler = null;

    public RemoveMessageTask(MessageService service, Message message) {
        this.service = service;
        this.message = message;
        this.handler = new Handler();
    }

    @Override
    public void run() {
        handler.post(new Runnable() {
            public void run() {
                service.removeMessage(message);
            }
        });
    }
}
