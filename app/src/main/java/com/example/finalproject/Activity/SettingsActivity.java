package com.example.finalproject.Activity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.finalproject.R;
import com.example.finalproject.databinding.SettingsActivityBinding;
import com.example.finalproject.modle.MyJobService;
import com.example.finalproject.modle.MyService;
import com.example.finalproject.modle.NotificationUtils;

import RoomDatabase.User;
import RoomDatabase.ViewModel;

public class SettingsActivity extends AppCompatActivity {
   public static SettingsActivityBinding binding;
    Intent intent;
    private boolean sound = false;
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = SettingsActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewModel viewModel = new ViewModelProvider(this).get(ViewModel.class);

        sp = getSharedPreferences("shared", MODE_PRIVATE);
        editor = sp.edit();
        intent = new Intent(getBaseContext(), MyService.class);
        
        binding.SettingImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!sound) {
                    editor.putBoolean("sound", false);
                    editor.apply();
                    binding.SettingImgSound.setVisibility(View.VISIBLE);
                    binding.SettingImg.setVisibility(View.GONE);
                    startService(intent);
                    sound = true;
                } else {
                    editor.putBoolean("sound", true);
                    editor.apply();
                    binding.SettingImgSound.setVisibility(View.GONE);
                    binding.SettingImg.setVisibility(View.VISIBLE);
                    stopService(intent);
                    sound = false;
                }
            }
        });
        binding.SettingImgSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!sound) {
                    editor.putBoolean("sound", false);
                    editor.apply();
                    binding.SettingImgSound.setVisibility(View.VISIBLE);
                    binding.SettingImg.setVisibility(View.GONE);
                    startService(intent);
                    sound = true;
                } else {
                    editor.putBoolean("sound", true);
                    editor.apply();
                    binding.SettingImgSound.setVisibility(View.GONE);
                    binding.SettingImg.setVisibility(View.VISIBLE);
                    stopService(intent);
                    sound = false;
                }
            }
        });
        binding.SettingProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), ProfileActivity.class));
            }
        });
        binding.SettingSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()){
                    //on
                    NotificationUtils.displayNotification(getBaseContext());
                }else {
                    //off
                    NotificationUtils.deleteNotificationChannel(getBaseContext());
                }
            }
        });
        binding.SettingReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SplashActivity.editor.clear();
                SplashActivity.editor.apply();
                viewModel.DeleteUser(new User(1,ProfileActivity.UserName,ProfileActivity.Email,
                        ProfileActivity.Birthday,ProfileActivity.Gender,ProfileActivity.Country));
                Toast.makeText(SettingsActivity.this, "Done", Toast.LENGTH_SHORT).show();
            }
        });
    }
}