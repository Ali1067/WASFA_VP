package com.qmobileme.wasfa;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.widget.Toast;

import static android.content.Context.MODE_PRIVATE;

public class Alarm_Broadcast  extends BroadcastReceiver {

    private final  int ID = 23;
    SharedPreferences sharedPreferences;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("BC_STARTS" , "ONRECIEVE");
       sharedPreferences= context.getSharedPreferences("MyPref", MODE_PRIVATE);
        create(context);
    }

    public void create(Context context)
    {
        Toast.makeText(context, "Alarm Triggered", Toast.LENGTH_SHORT).show();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setSmallIcon(R.drawable.clock);
        Log.i("BC_STARTS_TEXT" , sharedPreferences.getString("TITLE" , "") + " " +
        sharedPreferences.getString("DESCRIPTION" , ""));
        builder.setContentTitle(sharedPreferences.getString("TITLE" , ""));
        builder.setContentText(sharedPreferences.getString("DESCRIPTION" , ""));
        builder.setCategory(NotificationCompat.CATEGORY_REMINDER);
        builder.setDefaults(Notification.DEFAULT_SOUND);
         Notification notification = builder.build();
        NotificationManagerCompat.from(context).notify(ID,notification);
    }
}
