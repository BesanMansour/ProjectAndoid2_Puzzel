package com.example.finalproject.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.finalproject.Fragment.ChooseFragment;
import com.example.finalproject.Fragment.FillFragment;
import com.example.finalproject.Fragment.LevelAdapterFragment;
import com.example.finalproject.Fragment.TrueFalseFragment;
import com.example.finalproject.Json.ParsJson;
import com.example.finalproject.databinding.ActivityLevelBinding;

import java.util.ArrayList;
import java.util.List;

import RoomDatabase.Level;
import RoomDatabase.Mystery;
import RoomDatabase.ViewModel;

public class LevelActivity extends AppCompatActivity {
    ActivityLevelBinding binding;
    ArrayList<Fragment> fragments;
    List<Level> levelList;
    int point = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLevelBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        levelList = new ArrayList<>();
        fragments = new ArrayList<>();

        Intent intent = getIntent();
        int pos = intent.getIntExtra("position", 0) + 1;

        ViewModel viewModel = new ViewModelProvider(this).get(ViewModel.class);

        String assets = ParsJson.readFromAssets(getApplicationContext(), "json/jsonStr.json");
        ParsJson p = new ParsJson(this);
        p.readJson(assets);

        binding.LevelNumPoint.setText(point+"/8");

        viewModel.AllLevel().observe(this, new Observer<List<Level>>() {
            @Override
            public void onChanged(List<Level> levels) {
                int i = intent.getIntExtra("position", 0);
                levelList = levels;
                binding.LevelTV.setText("المجموعة " + levelList.get(i).getId());
            }
        });
        viewModel.AllMystery().observe(this, new Observer<List<Mystery>>() {
            @Override
            public void onChanged(List<Mystery> mysteries) {
                for (int i = 0; i < mysteries.size(); i++) {
                    int level = mysteries.get(i).getLevelId();

                    if (level == pos) {
                        String title = mysteries.get(i).getTitle();
                        String true_answer = mysteries.get(i).getTrue_answer();
                        String answer1 = mysteries.get(i).getAnswer_1();
                        String answer2 = mysteries.get(i).getAnswer_2();
                        String answer3 = mysteries.get(i).getAnswer_3();
                        String answer4 = mysteries.get(i).getAnswer_4();
                        int patternId = mysteries.get(i).getPatternId();

                        switch (patternId) {
                            case 1:
                                fragments.add(TrueFalseFragment.newInstance(title,true_answer));
                                break;
                            case 2:
                                fragments.add(ChooseFragment.newInstance(title, answer1, answer2, answer3, answer4, true_answer));
                                break;
                            case 3:
                                fragments.add(FillFragment.newInstance(title,true_answer));
                                break;
                        }
                        LevelAdapterFragment levelAdapterFragment = new LevelAdapterFragment(LevelActivity.this, fragments);
                        binding.LevelPager.setAdapter(levelAdapterFragment);
                    }
                }
            }
        });
    }
}