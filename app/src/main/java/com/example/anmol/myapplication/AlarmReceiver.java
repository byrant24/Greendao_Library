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
    private NotificationManager notificationManager;
    @Override
    public void onReceive(final Context context, Intent intent) {

        Log.d("hh","enter");

        String msg = intent.getStringExtra("message");
//        this will update the UI with message
        //this will sound the alarm tone
        //this will sound the alarm once, if you wish to
        //raise alarm in loop continuously then use MediaPlayer and setLooping(true)
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
                context).setContentTitle("Alarm")
                .setContentText(msg)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentIntent(contentIntent)
                .build();
        notificationManager.notify(1, alarmNotification);
        Log.d("AlarmReceiver", "Notification sent.");


    }

}
