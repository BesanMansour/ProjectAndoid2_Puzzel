package com.example.finalproject.modle;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.finalproject.Activity.SplashActivity;
import com.example.finalproject.R;

public class NotificationUtils {
    public static final String CHANNEL_ID = "channel_id";


    public static void displayNotification(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel =
                    new NotificationChannel(CHANNEL_ID, "CHANNEL display name",
                            NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("my channel description");

            NotificationManager  manager = context.getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        Intent intent = new Intent(context, SplashActivity.class);
        PendingIntent pi = PendingIntent.getActivity(context, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder
                (context, CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_baseline_add_circle_24)
                .setContentTitle("ألغاز")
                .setContentText("text")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setStyle(new NotificationCompat.BigTextStyle().bigText("هيا تعال وأكمل رحالتك في اللعبة!"))
                .addAction(R.drawable.ic_baseline_close_24, "العب الان", pi);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(context);
        managerCompat.notify(1, builder.build());
    }

    public static void deleteNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.deleteNotificationChannel(CHANNEL_ID);
        }
    }

}
