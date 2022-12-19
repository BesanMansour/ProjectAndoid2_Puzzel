package com.example.finalproject.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.finalproject.R;
import com.example.finalproject.modle.MyService;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread thread = new Thread(){
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

        Intent intent = new Intent(getBaseContext(), MyService.class);
        startService(intent);
    }
}