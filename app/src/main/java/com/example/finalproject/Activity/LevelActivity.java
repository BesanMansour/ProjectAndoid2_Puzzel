package com.example.finalproject.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.DecimalFormat;
import android.icu.text.NumberFormat;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalproject.Fragment.ChooseFragment;
import com.example.finalproject.Fragment.FillFragment;
import com.example.finalproject.Fragment.LevelAdapterFragment;
import com.example.finalproject.Fragment.TrueFalseFragment;
import com.example.finalproject.Json.ParsJson;
import com.example.finalproject.R;
import com.example.finalproject.databinding.ActivityLevelBinding;
import com.example.finalproject.modle.AdapterStartPlay;
import com.example.finalproject.modle.ListenerScore;
import com.example.finalproject.modle.MyListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import RoomDatabase.Level;
import RoomDatabase.Mystery;
import RoomDatabase.ViewModel;

public class LevelActivity extends AppCompatActivity implements TrueFalseFragment.trueScore, ChooseFragment.chooseScore, FillFragment.fillScore {
    public static ActivityLevelBinding binding;
    public static ArrayList<Fragment> fragments;
    public static MediaPlayer media_fail,media_win;
   public static LevelAdapterFragment levelAdapterFragment;
    public static final String CountQus = "CountQ";
    public static final String CountLevel = "CountLevel";
    public static final String CountTQus = "CountTQ";
    public static final String CountFQus = "CountFQ";
    public static final String Score = "Score";
    public static final String PAGE = "page";
    public static int CountTrue;
    public static int CountFalse;
    public static int CountQ;
    public static int Count_level;
    int levelScore;
    int new_score;
    int page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLevelBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String language = getApplicationContext().getResources().getConfiguration().locale.getLanguage();
        if (language.equals("en")) {
            // Language is English
            String assetsEn = ParsJson.readFromAssets(getApplicationContext(), "json/english.json");
            ParsJson p = new ParsJson(this);
            p.readJson(assetsEn);
        } else if (language.equals("ar")) {
            // Language is Arabic
            String assetsAr = ParsJson.readFromAssets(getApplicationContext(), "json/jsonStr.json");
            ParsJson p = new ParsJson(this);
            p.readJson(assetsAr);
        }
        media_fail = MediaPlayer.create(this, R.raw.fail_sound);
        media_win = MediaPlayer.create(this, R.raw.win_sound);

        fragments = new ArrayList<>();


        Intent intent = getIntent();
        int pos = intent.getIntExtra("position", 0);

        ViewModel viewModel = new ViewModelProvider(this).get(ViewModel.class);

        binding.LevelTV.setText("Level \n"+pos);

        viewModel.getQuestion(pos).observe(this, new Observer<List<Mystery>>() {
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
                        String hint = mysteries.get(i).getHint();
                        int patternId = mysteries.get(i).getPatternId();
                        int duration = mysteries.get(i).getDuration();
                        int point = mysteries.get(i).getPoints();

                        switch (patternId) {
                            case 1:
                                fragments.add(TrueFalseFragment.newInstance(title, true_answer, hint, duration, point));
                                break;

                            case 2:
                                fragments.add(ChooseFragment.newInstance(title, answer1, answer2, answer3, answer4, true_answer, hint, duration, point));
                                break;

                            case 3:
                                fragments.add(FillFragment.newInstance(title, true_answer, hint, duration, point));
                                break;
                        }
                        levelAdapterFragment = new LevelAdapterFragment(LevelActivity.this, fragments);
                        binding.LevelPager.setAdapter(levelAdapterFragment);
                    }
                }
            }
        });
        if (SplashActivity.sp.getInt("new_score",0)==8){
            SplashActivity.editor.putInt(CountLevel,SplashActivity.sp.getInt("new_score",0));
            SplashActivity.editor.apply();
            Toast.makeText(this, SplashActivity.sp.getInt("new_score",0)+"", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
        binding.LevelPager.setUserInputEnabled(false);
//        binding.LevelNext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Toast.makeText(getBaseContext(), binding.LevelPager.getCurrentItem()+"", Toast.LENGTH_SHORT).show();
//                page = binding.LevelPager.getCurrentItem();
//                if (page != 2) {
//                    binding.LevelPager.setCurrentItem(page + 1, true);
//                    levelAdapterFragment.notifyItemChanged(1);
//                } else {
//                    startActivity(new Intent(LevelActivity.this, StartPlayingActivity.class));
//                }
//
//            }
//        });
        binding.LinearSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pager = binding.LevelPager.getCurrentItem();
                if (pager != 2) {
                    binding.LevelPager.setCurrentItem(pager + 1, true);
                    levelAdapterFragment.notifyItemChanged(1);
                } else {
                    startActivity(new Intent(LevelActivity.this, StartPlayingActivity.class));
                }
                levelScore=levelScore-3;

            }
        });
    }

    @Override
    public void TFQ(int score) {
        levelScore = score;
        binding.LevelScore.setText(levelScore + "/8 \n"+"Marks");

    }

    @Override
    public void ChQ(int score) {
        levelScore += score;
        binding.LevelScore.setText(levelScore + "/8 \n"+"Marks");
    }

    @Override
    public void FillQ(int score) {
        levelScore += score;
        binding.LevelScore.setText(levelScore + "/8 \n"+"Marks");
        new_score = levelScore;
        SplashActivity.editor.putInt("new_score",new_score);
        SplashActivity.editor.apply();
        Toast.makeText(LevelActivity.this, "new_scoreFill "+new_score, Toast.LENGTH_SHORT).show();//8
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        SplashActivity.editor.putInt(PAGE,page);
        SplashActivity.editor.apply();
        Toast.makeText(this, "done", Toast.LENGTH_SHORT).show();
    }
}