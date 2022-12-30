package com.example.finalproject.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import RoomDatabase.Level;
import RoomDatabase.Mystery;
import RoomDatabase.ViewModel;

public class LevelActivity extends AppCompatActivity {
    public static ActivityLevelBinding binding;
    public static ArrayList<Fragment> fragments;
    public static MediaPlayer media_fail;
    public static MediaPlayer media_win;
    public static int score;
    public static SharedPreferences sp;
    public static SharedPreferences.Editor editor;
    LevelAdapterFragment levelAdapterFragment;
    String title;
    int point;
    int duration;
    int patternId;
    String hint;
    String answer4;
    String answer3;
    String answer2;
    String answer1;
    String true_answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLevelBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sp = getSharedPreferences("Login", MODE_PRIVATE);
        editor = sp.edit();

        media_fail = MediaPlayer.create(this, R.raw.fail_sound);
        media_win = MediaPlayer.create(this, R.raw.win_sound);

        fragments = new ArrayList<>();

        Intent intent = getIntent();
        int pos = intent.getIntExtra("position", 0);

        ViewModel viewModel = new ViewModelProvider(this).get(ViewModel.class);
        Locale currentLocale = Locale.getDefault();
        String languageCode = currentLocale.getLanguage();
        if (languageCode.equals("en")) {
            // Language is English
            String assets = ParsJson.readFromAssets(getApplicationContext(), "json/english.json");
            ParsJson p = new ParsJson(this);
            p.readJson(assets);

        } else if (languageCode.equals("ar")) {
            // Language is Arabic
            String assets = ParsJson.readFromAssets(getApplicationContext(), "json/jsonStr.json");
            ParsJson p = new ParsJson(this);
            p.readJson(assets);
        }

        binding.LevelTV.setText("المجموعة " + pos);

//        TextView tv = findViewById(R.id.scoreTrue);
        int true_false = sp.getInt("TueFalsePoint", TrueFalseFragment.point);
        Log.d("true_false", true_false + "");
        Toast.makeText(this, true_false + "", Toast.LENGTH_SHORT).show();
//        TrueFalseFragment.binding.scoreTrue.setText(true_false + "");

        viewModel.getQuestion(pos).observe(this, new Observer<List<Mystery>>() {
            @Override
            public void onChanged(List<Mystery> mysteries) {
                for (int i = 0; i < mysteries.size(); i++) {
                    int level = mysteries.get(i).getLevelId();

                    if (level == pos) {
                        title = mysteries.get(i).getTitle();
                        true_answer = mysteries.get(i).getTrue_answer();
                        answer1 = mysteries.get(i).getAnswer_1();
                        answer2 = mysteries.get(i).getAnswer_2();
                        answer3 = mysteries.get(i).getAnswer_3();
                        answer4 = mysteries.get(i).getAnswer_4();
                        hint = mysteries.get(i).getHint();
                        patternId = mysteries.get(i).getPatternId();
                        duration = mysteries.get(i).getDuration();
                        point = mysteries.get(i).getPoints();

                        switch (patternId) {
                            case 1:
                                fragments.add(TrueFalseFragment.newInstance(title, true_answer, hint, duration, point));
//                                    new CountDownTimer(duration, 1000) {
//                                    @Override
//                                    public void onTick(long l) {
////                                        NumberFormat f = new DecimalFormat("00");
//                                        NumberFormat f = null;
//                                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
//                                            f = new DecimalFormat("00");
//                                            long hour = (l / 3600000) % 24;
//                                            long min = (l / 60000) % 60;
//                                            long sec = (l / 1000) % 60;
//                                            binding.LevelTimer.setText(f.format(hour) + ":" + f.format(min) + ":" + f.format(sec));
//                                        }
//                                    }
//                                    @Override
//                                    public void onFinish() {
//                                        binding.LevelTimer.setText("00:00:00");
//                                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                                        transaction.replace(R.id.LevelPager,ChooseFragment.newInstance(title, answer1, answer2, answer3, answer4, true_answer, hint, duration, point));
//                                        transaction.addToBackStack(null);
//                                        transaction.commit();
//                                        //Todo: chenge this fragment to next one
//                                    }
//                                }.start();
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
        binding.LevelSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragments.set(1, ChooseFragment.newInstance(title, answer1, answer2, answer3, answer4, true_answer, hint, duration, point));
                binding.LevelPager.setCurrentItem(1, true);
//                binding.LevelPager.getCurrentItem();
                levelAdapterFragment.notifyItemChanged(1);
            }
        });
    }
}