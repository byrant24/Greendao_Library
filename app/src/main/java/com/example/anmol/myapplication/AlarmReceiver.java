package com.example.anmol.myapplication;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

/**
 * Created by Anmol on 30-04-2016.
 */
public class AlarmReceiver extends BroadcastReceiver {
    DbHelper db;

    private NotificationManager notificationManager;
    @Override
    public void onReceive(final Context context, Intent intent) {

        Log.d("hh","enter");
        db=new DbHelper(context);

        String msg = intent.getStringExtra("message");

//        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
//        if (alarmUri == null) {
//            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        }
//        Ringtone ringtone = RingtoneManager.getRingtone(context, alarmUri);
//        ringtone.play();
        Log.d("AlarmReceiver", "Preparing to send notification...: " + msg);
        //this will send a notification message
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                new Intent(context, MainActivity.class), 0);
        Notification alarmNotification = new Notification.Builder(
                context).setContentTitle("Todo Task")
                .setContentText(msg)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setSmallIcon(R.drawable.ic_stat_name)
                .setContentIntent(contentIntent)
                .build();
        notificationManager.notify(1, alarmNotification);
        Log.d("AlarmReceiver", "Notification sent.");
        db.update(msg);
    }

}
