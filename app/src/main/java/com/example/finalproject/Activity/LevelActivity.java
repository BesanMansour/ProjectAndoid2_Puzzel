package com.example.finalproject.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.finalproject.Fragment.ChooseFragment;
import com.example.finalproject.Fragment.FillFragment;
import com.example.finalproject.Fragment.LevelAdapterFragment;
import com.example.finalproject.Fragment.TrueFalseFragment;
import com.example.finalproject.Json.ParsJson;
import com.example.finalproject.databinding.ActivityLevelBinding;

import java.util.ArrayList;
import java.util.List;

import RoomDatabase.Mystery;
import RoomDatabase.ViewModel;

public class LevelActivity extends AppCompatActivity {
    ActivityLevelBinding binding;
    ArrayList<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLevelBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        int pos = intent.getIntExtra("position", 0)+1;

        Log.d("Pos",pos+"");
        ViewModel viewModel = new ViewModelProvider(this).get(ViewModel.class);

        String assets = ParsJson.readFromAssets(getApplicationContext(), "json/jsonStr.json");
        ParsJson p = new ParsJson(this);
        p.readJson(assets);

        fragments = new ArrayList<>();
        LevelAdapterFragment levelAdapterFragment = new LevelAdapterFragment(this, fragments);

        viewModel.AllMystery().observe(this, new Observer<List<Mystery>>() {
            @Override
            public void onChanged(List<Mystery> mysteries) {
                for (int i = 0; i < mysteries.size(); i++) {
                    int  level = mysteries.get(i).getLevelId();
                    if(level == pos){
                    String title = mysteries.get(i).getTitle();
                    String true_answer = mysteries.get(i).getTrue_answer();
                    String answer1 = mysteries.get(i).getAnswer_1();
                    String answer2 = mysteries.get(i).getAnswer_2();
                    String answer3 = mysteries.get(i).getAnswer_3();
                    String answer4 = mysteries.get(i).getAnswer_4();

                    fragments.add(TrueFalseFragment.newInstance(title, Boolean.parseBoolean(true_answer)));
                    fragments.add(ChooseFragment.newInstance(title, answer1, answer2, answer3, answer4, true_answer));
                    fragments.add(FillFragment.newInstance(title));

                    Log.d("fragment", String.valueOf(fragments.size()));

                    binding.LevelPager.setAdapter(levelAdapterFragment);
                }}
            }
        });
    }
}