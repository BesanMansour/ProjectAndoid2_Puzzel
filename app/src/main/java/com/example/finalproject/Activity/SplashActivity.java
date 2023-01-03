package com.example.finalproject.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.example.finalproject.R;
import com.example.finalproject.databinding.ActivitySplashBinding;
import com.example.finalproject.modle.MyJobService;
import com.example.finalproject.modle.MyService;

public class SplashActivity extends AppCompatActivity {
    ActivitySplashBinding binding;
    public static SharedPreferences sp;
    public static SharedPreferences.Editor editor;
    public static JobScheduler jobScheduler;
    Animation img,tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        jobService();
        img =AnimationUtils.loadAnimation(this, R.anim.splash_img);
        tv =AnimationUtils.loadAnimation(this, R.anim.splash_tv);
        binding.imageView.setAnimation(img);
        binding.SplashTV.setAnimation(tv);

        sp = getSharedPreferences("shared", MODE_PRIVATE);
        editor = sp.edit();

        Intent intent = new Intent(getBaseContext(), MyService.class);
        startService(intent);

        if (sp.getBoolean("sound", true)) {
            stopService(intent);
        } else if (sp.getBoolean("sound", false)) {
            startService(intent);
        }
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

        SplashActivity.sp.getInt(LevelActivity.CountQus, 0);
        SplashActivity.sp.getInt(LevelActivity.CountTQus, 0);
        SplashActivity.sp.getInt(LevelActivity.CountFQus, 0);
        SplashActivity.sp.getInt(LevelActivity.CountLevel, 0);
//        SplashActivity.sp.getInt("new_score", 0);
    }

    public void jobService() {
        jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        ComponentName componentName = new ComponentName(getBaseContext(), MyJobService.class);
        JobInfo jobInfo = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            jobInfo = new JobInfo.Builder(1, componentName)
                    .setPeriodic(24 * 60 * 60 * 1000, JobInfo.getMinFlexMillis())
                    .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                    .build();
        }
        jobScheduler.schedule(jobInfo);
    }
}

//TODO: عند الخروج من التطبيق والعودة مرة أخرى يجب العودة لنفس اللغز الذي توقف عنده
//المستخدم.

//TODO:باإلضافة إلى زر تخطي skip تخطي اللغز الحالي: حيث يتم خصم 3 نقاط عند تخطي كل
//لغز.

//TODO :التقييم العام للمرحلة-وهو متوسط النقاط التي حصل عليها في جميع ألغاز هذه المرحلة-(

//TODO:وعدد المراحل التي أنهاها