package com.example.hyeon.part7_20;

import android.app.ActivityManager;
import android.app.IntentService;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import java.util.List;

public class Lab4Service extends IntentService {
    public Lab4Service() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);

        List<ActivityManager.RunningTaskInfo> list = manager.getRunningTasks(1);
        ActivityManager.RunningTaskInfo info = list.get(0);
        if(info.topActivity.getClassName().equals("com.example.hyeon.part7_20.Lab20_3Activity")) {
            Intent sIntent = new Intent("com.example.ACTION_TO_ACTIVITY");
            sIntent.putExtra("message", "This Message is from Service");
            sendBroadcast(sIntent);
        } else {
            // Lab3Activity가 화면에 보이지 않는다면
            NotificationManager notiManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
            NotificationCompat.Builder builder = null;

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                String channelId = "one-channel";
                String channelName = "My Channel One";
                String channelDescription = "My Channel One Description";
                NotificationChannel channel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT);
                channel.setDescription(channelDescription);
                builder = new android.support.v4.app.NotificationCompat.Builder(this, channelId);
            } else {
                builder = new NotificationCompat.Builder(this);
            }

            builder.setSmallIcon(android.R.drawable.ic_notification_overlay);
            builder.setWhen(System.currentTimeMillis());
            builder.setContentTitle("Message");
            builder.setAutoCancel(true);
            builder.setContentText("This Message is from Service");
            Intent aIntent = new Intent(this, Lab20_3Activity.class);
            PendingIntent pIntent = PendingIntent.getActivity(this, 10, aIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(pIntent);
            notiManager.notify(222, builder.build());
        }
    }
}
