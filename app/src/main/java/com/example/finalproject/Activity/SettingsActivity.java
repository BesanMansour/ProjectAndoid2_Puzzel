package com.example.finalproject.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.finalproject.R;
import com.example.finalproject.databinding.SettingsActivityBinding;
import com.example.finalproject.modle.MyService;

public class SettingsActivity extends AppCompatActivity {
    SettingsActivityBinding binding;
    Intent intent;
    private boolean sound = false;
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = SettingsActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sp = getSharedPreferences("shared", MODE_PRIVATE);
        editor = sp.edit();
        intent = new Intent(getBaseContext(), MyService.class);

        binding.SettingImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!sound) {
                    editor.putBoolean("sound",false);
                    binding.SettingImg.setImageResource(R.drawable.img);
                    startService(intent);
                    sound = true;
                } else {
                    editor.putBoolean("sound",true);
                    binding.SettingImg.setImageResource(R.drawable.img_1);
                    stopService(intent);
                    sound = false;
                }
            }
        });
        sp.getString("sound","");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(intent);
    }
//    @RequiresApi(api = Build.VERSION_CODES.M)
//    private void mute() {
//        //mute audio
//        AudioManager amanager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
//        amanager.setStreamMute(AudioManager.STREAM_NOTIFICATION, true);
//    }
//
//    public void unmute() {
//        //unmute audio
//        AudioManager amanager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
//        amanager.setStreamMute(AudioManager.STREAM_NOTIFICATION, false);
//    }
}