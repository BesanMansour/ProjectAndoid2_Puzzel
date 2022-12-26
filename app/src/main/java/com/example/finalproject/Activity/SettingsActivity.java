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
                    editor.putBoolean("sound_false", false);
                    editor.apply();
                    binding.SettingImg.setImageResource(R.drawable.img);
//                    startService(intent);
                    sound = true;
                } else {
                    editor.putBoolean("sound_true", true);
                    editor.apply();
                    binding.SettingImg.setImageResource(R.drawable.img_1);
//                    stopService(intent);
                    sound = false;
                }
            }
        });
    }
}