# ChatOnline
test
```
public class MessengerUtil {

    private static Messenger m_service;
    private static List<Handler> m_handlers = new LinkedList<>();

    private MessengerUtil() {
    }

    private static Messenger getMessenger() {
        return m_service;
    }

    private static ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            m_service = new Messenger(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    public static void bindService(Activity activity) {
        Intent intent = new Intent(activity, MyService.class);
        activity.bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    public static void registerMessage(Handler handler) {
        m_handlers.add(handler);
    }

    public static void sendMessageToService(Message message){
        try {
            getMessenger().send(message);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static void handleMessage(Message message){
        for (Handler m_handler : m_handlers) {
            m_handler.handleMessage(message);
        }
    }


}

```
