package com.qmobileme.wasfa;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.widget.Toast;

public class Alarm_Broadcast  extends BroadcastReceiver {

    private final  int ID = 23;
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("BC_STARTS" , "ONRECIEVE");
        create(context);
    }

    public void create(Context context)
    {
        Toast.makeText(context, "Alarm Triggered", Toast.LENGTH_SHORT).show();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setSmallIcon(R.drawable.clock);
        builder.setContentTitle("Notification Text");
        builder.setContentText("TESTING OF NOTIF");
        builder.setCategory(NotificationCompat.CATEGORY_REMINDER);
        builder.setDefaults(Notification.DEFAULT_SOUND);
         Notification notification = builder.build();
        NotificationManagerCompat.from(context).notify(ID,notification);
    }
}
