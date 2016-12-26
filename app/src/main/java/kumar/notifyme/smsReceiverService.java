package kumar.notifyme;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class smsReceiverService extends Service {

    static final String tag ="smsReceiverService";
    Intent intent ,notificationIntent;
    PendingIntent pendingIntent;
    NotificationManager nm;
    static String parsedMsgConetnt = new String("");
    Notification.Builder notification;
    Context context= this.getBaseContext();
    int notifyId = 123;
    public smsReceiverService() {
        Log.d(tag,"started service");
    }

    @Override
    public void onCreate()
    {
        Log.d(tag,"oncreate");

        nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);


    }
    @Override
    public int onStartCommand(Intent intent,
                               int flags,
                               int startId)
    {
        Log.d(tag,"on start command");
        this.parsedMsgConetnt=intent.getStringExtra("msgcontent");
        Log.d(tag,""+this.parsedMsgConetnt);
        notification = new Notification.Builder(this)
                .setTicker("something")
                .setSmallIcon(R.drawable.pi)
                .setAutoCancel(true)
                .setWhen(System.currentTimeMillis());

        notificationIntent = new Intent(this, HomePage.class);
        pendingIntent = PendingIntent.getActivity(this,0,notificationIntent,0);
        notification.setContentIntent(pendingIntent);
        notification.setContentTitle("Content ");
        notification.setContentText(this.parsedMsgConetnt);
//        inclusive or bitwise function
//         notification.build().flags = notification.build().flags | Notification.FLAG_AUTO_CANCEL;
        notification.build().flags |= Notification.FLAG_AUTO_CANCEL;
        Log.d(tag,"start notification");
        nm.notify(notifyId,notification.build());
        Log.d(tag,"stop the service");
        this.stopSelf();
        return startId;
    }

//    @Override
//    public void onDestroy()
//    {
//
//    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
