package com.example.finalproject.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import com.example.finalproject.R;
import com.example.finalproject.modle.MyJobService;
import com.example.finalproject.modle.MyService;

public class SplashActivity extends AppCompatActivity {
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    public static JobScheduler jobScheduler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        jobService();

        sp = getSharedPreferences("shared", MODE_PRIVATE);
        editor = sp.edit();

        Intent intent = new Intent(getBaseContext(), MyService.class);
//        startService(intent);

//        if (sp.getBoolean("sound_true", true)) {
//            stopService(intent);
//        } else if (sp.getBoolean("sound_false", false)) {
//            startService(intent);
//        }
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(2000);
                    Intent intent = new Intent(getBaseContext(), HomeActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }
    public void jobService() {
        jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        ComponentName componentName = new ComponentName(getBaseContext(), MyJobService.class);
        JobInfo jobInfo = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            jobInfo = new JobInfo.Builder(1, componentName)
                    .setPeriodic(24 * 60 * 60 * 1000, 10*60*1000)
                    .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                    .build();
        }
        jobScheduler.schedule(jobInfo);
    }
}