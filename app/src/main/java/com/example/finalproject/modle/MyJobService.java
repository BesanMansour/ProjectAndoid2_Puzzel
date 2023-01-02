package com.example.finalproject.modle;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.finalproject.Activity.HomeActivity;
import com.example.finalproject.Activity.SplashActivity;
import com.example.finalproject.R;

public class MyJobService extends JobService {

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        NotificationUtils.displayNotification(this);
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        SplashActivity.jobScheduler.cancel(1);
        return true;
    }
//    public void displayNotification() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            NotificationChannel channel =
//                    new NotificationChannel(CHANNEL_ID, "CHANNEL display name",
//                            NotificationManager.IMPORTANCE_DEFAULT);
//            channel.setDescription("my channel description");
//
//            NotificationManager  manager = getSystemService(NotificationManager.class);
//            manager.createNotificationChannel(channel);
//        }
//
//        Intent intent = new Intent(this, SplashActivity.class);
//        PendingIntent pi = PendingIntent.getActivity(getBaseContext(), 0, intent, 0);
//
//        NotificationCompat.Builder builder = new NotificationCompat.Builder
//                (getBaseContext(), CHANNEL_ID);
//        builder.setSmallIcon(R.drawable.ic_baseline_add_circle_24)
//                .setContentTitle("ألغاز")
//                .setContentText("text")
//                .setPriority(NotificationCompat.PRIORITY_HIGH)
//                .setStyle(new NotificationCompat.BigTextStyle().bigText("هيا تعال وأكمل رحالتك في اللعبة!"))
//                .addAction(R.drawable.ic_baseline_close_24, "العب الان", pi);
//
//        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
//        managerCompat.notify(1, builder.build());
//    }
}
