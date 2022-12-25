package com.example.finalproject.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.finalproject.Json.ParsJson;
import com.example.finalproject.databinding.ActivityStartPlayingBinding;
import com.example.finalproject.modle.AdapterStartPlay;
import com.example.finalproject.modle.MyListener;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import RoomDatabase.Level;
import RoomDatabase.ViewModel;

public class StartPlayingActivity extends AppCompatActivity {
    ActivityStartPlayingBinding binding;
    List<Level> level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStartPlayingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewModel viewModel = new ViewModelProvider(this).get(ViewModel.class);

        String assets = ParsJson.readFromAssets(getApplicationContext(),"json/jsonStr.json");

        ParsJson p = new ParsJson(this);
        p.readJson(assets);

        level= new ArrayList<>();
        viewModel.AllLevel().observe(this, new Observer<List<Level>>() {
            @Override
            public void onChanged(List<Level> levels) {
                level = levels;
                AdapterStartPlay adapterStartPlay = new AdapterStartPlay(getApplicationContext(),level, new MyListener() {
                    @Override
                    public void onClick(int position) {
                        Intent intent = new Intent(getBaseContext(), LevelActivity.class);
                        intent.putExtra("position",position);
                        startActivity(intent);
                    }
                });
                binding.StartPlayingRecycler.setAdapter(adapterStartPlay);
                binding.StartPlayingRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                        RecyclerView.VERTICAL,false));
                adapterStartPlay.notifyDataSetChanged();
            }
        });
    }
}