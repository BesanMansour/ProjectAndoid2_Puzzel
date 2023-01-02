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
    public static MediaPlayer media_fail;
    public static MediaPlayer media_win;
   public static LevelAdapterFragment levelAdapterFragment;
    public static final String CountQus = "CountQ";
    public static final String CountLevel = "CountLevel";
    public static final String CountTQus = "CountTQ";
    public static final String CountFQus = "CountFQ";
    public static final String Score = "Score";
    public static int CountTrue;
    public static int CountFalse;
    public static int CountQ;
    int levelScore;
    int scoreShared;

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

        Toast.makeText(this, getApplicationContext().getResources().getConfiguration().locale.getLanguage(), Toast.LENGTH_SHORT).show();
        media_fail = MediaPlayer.create(this, R.raw.fail_sound);
        media_win = MediaPlayer.create(this, R.raw.win_sound);

        fragments = new ArrayList<>();

        levelScore += scoreShared;
        scoreShared = SplashActivity.sp.getInt("levelScore",0);

        Intent intent = getIntent();
        int pos = intent.getIntExtra("position", 0);

        ViewModel viewModel = new ViewModelProvider(this).get(ViewModel.class);
        Locale currentLocale = Locale.getDefault();
//        String languageCode = currentLocale.getLanguage();
//        String language= getResources().getConfiguration().locale.getDisplayLanguage();


//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//        } else {
//
//        }
//        binding.LevelScore.setText(sp.getInt("Score", 0)+"");
        binding.LevelTV.setText("المجموعة \n"+pos);

//        TextView tv = findViewById(R.id.scoreTrue);
//        int true_false = sp.getInt("Score",0);
//
//        Log.d("true_false", true_false + "");
//        Toast.makeText(this, true_false + "", Toast.LENGTH_SHORT).show();
//        TrueFalseFragment.binding.scoreTrue.setText(true_false + "");

//        Log.d("scoreLevel", SplashActivity.sp.getInt("levelScore",0)+ "");
//        scoreShared = SplashActivity.sp.getInt("levelScore",0);
//        levelScore += scoreShared;
////        scoreShared = AdapterStartPlay.score;
//        binding.LevelScore.setText(levelScore+ "/6 \n"+"Marks");

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
        binding.LevelPager.setUserInputEnabled(false);

        binding.LevelNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pager = binding.LevelPager.getCurrentItem();
                if (pager != 2) {
                    binding.LevelPager.setCurrentItem(pager + 1, true);
                    levelAdapterFragment.notifyItemChanged(1);
                } else {
                    startActivity(new Intent(LevelActivity.this, StartPlayingActivity.class));
                }
            }
        });
        binding.LevelSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pager = binding.LevelPager.getCurrentItem();
                if (pager != 2) {
                    binding.LevelPager.setCurrentItem(pager + 1, true);
                    levelAdapterFragment.notifyItemChanged(1);
                    levelScore = levelScore-3;
                } else {
                    levelScore = levelScore-3;
                    startActivity(new Intent(LevelActivity.this, StartPlayingActivity.class));
                }
            }
        });


    }

    @Override
    public void TFQ(int score) {
        levelScore = score;
        SplashActivity.editor.putInt("levelScore", levelScore);
        SplashActivity.editor.apply();
//        scoreShared = SplashActivity.sp.getInt("levelScore",0);
//        levelScore += scoreShared;
//        scoreShared = AdapterStartPlay.score;
//        binding.LevelScore.setText(levelScore+ "/6 \n"+"Marks");
        binding.LevelScore.setText(levelScore + "/6 \n"+"Marks");

    }

    @Override
    public void ChQ(int score) {
        levelScore += score;
        SplashActivity.editor.putInt("levelScore", levelScore);
        SplashActivity.editor.apply();
//        scoreShared = SplashActivity.sp.getInt("levelScore",0);
//        levelScore += scoreShared;
        //        scoreShared = AdapterStartPlay.score;
//        binding.LevelScore.setText(levelScore+ "/6 \n"+"Marks");
        binding.LevelScore.setText(levelScore + "/6 \n"+"Marks");
    }

    @Override
    public void FillQ(int score) {
        levelScore += score;
        SplashActivity.editor.putInt("levelScore", levelScore);
        SplashActivity.editor.apply();
//        scoreShared = SplashActivity.sp.getInt("levelScore",0);
//        levelScore += scoreShared;
//        scoreShared = AdapterStartPlay.score;
//        binding.LevelScore.setText(levelScore+ "/6 \n"+"Marks");
        binding.LevelScore.setText(levelScore + "/6 \n"+"Marks");
    }
}