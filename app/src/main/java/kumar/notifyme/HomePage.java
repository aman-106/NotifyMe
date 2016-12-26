package kumar.notifyme;

import android.app.ActivityManager;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.service.notification.StatusBarNotification;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class HomePage extends AppCompatActivity {
    NotificationManager nm ;
    private int notificationid = 123;
    Intent intent;
    StatusBarNotification[] notificationStaus ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
    }
//     always onStart must call super.onstart()
//    @Override
//    protected void onStart()
//    {
////        if(Build.VERSION.SDK_INT >= 5.0)
////        notificationStaus = nm.getActiveNotifications();
////        nm.cancel(notificationid);
//    }
}
