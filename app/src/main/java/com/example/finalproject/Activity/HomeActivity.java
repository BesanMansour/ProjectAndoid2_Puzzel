package com.example.finalproject.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.example.finalproject.databinding.ActivityHomeBinding;
import com.example.finalproject.modle.MyJobService;
import com.example.finalproject.modle.MyService;

import java.util.ArrayList;
import java.util.List;

import RoomDatabase.User;
import RoomDatabase.ViewModel;

public class HomeActivity extends AppCompatActivity {
    ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewModel viewModel = new ViewModelProvider(this).get(ViewModel.class);
        viewModel.AllUser().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                if (users.size() == 0) {
                    viewModel.InsertUser(new User(1, "User1", null, null, null, null));
                }
            }
        });

        binding.HomeStartPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), StartPlayingActivity.class));
            }
        });
        binding.HomeLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        binding.HomeSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), SettingsActivity.class));
            }
        });
    }

//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        Intent intent = new Intent(getBaseContext(), MyService.class);
//        stopService(intent);
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intent = new Intent(getBaseContext(), MyService.class);
        stopService(intent);
    }
}
