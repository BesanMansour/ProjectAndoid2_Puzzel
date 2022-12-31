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
import com.example.finalproject.modle.MyListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import RoomDatabase.Level;
import RoomDatabase.Mystery;
import RoomDatabase.ViewModel;

public class LevelActivity extends AppCompatActivity implements MyListener {
    public static ActivityLevelBinding binding;
    public static ArrayList<Fragment> fragments;
    public static MediaPlayer media_fail;
    public static MediaPlayer media_win;
//    public static SharedPreferences sp;
//    public static SharedPreferences.Editor editor;
    LevelAdapterFragment levelAdapterFragment;
    public static final String CountQus = "CountQ";
    public static final String CountLevel = "CountLevel";
    public static final String CountTQus = "CountTQ";
    public static final String CountFQus = "CountFQ";
    public static final String Score = "Score";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLevelBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        String language = getApplicationContext().getResources().getConfiguration().locale.getLanguage();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N && language.equals("en")) {
            // Language is English
            String assets = ParsJson.readFromAssets(getApplicationContext(), "json/english.json");
            ParsJson p = new ParsJson(this);
            p.readJson(assets);

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N && language.equals("ar")) {
            // Language is Arabic
            String assets = ParsJson.readFromAssets(getApplicationContext(), "json/jsonStr.json");
            ParsJson p = new ParsJson(this);
            p.readJson(assets);
        }

//        sp = getSharedPreferences("Login", MODE_PRIVATE);
//        editor = sp.edit();

        media_fail = MediaPlayer.create(this, R.raw.fail_sound);
        media_win = MediaPlayer.create(this, R.raw.win_sound);

        fragments = new ArrayList<>();



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
        binding.LevelTV.setText("المجموعة " + pos);

//        TextView tv = findViewById(R.id.scoreTrue);
//        int true_false = sp.getInt("Score",0);
//
//        Log.d("true_false", true_false + "");
//        Toast.makeText(this, true_false + "", Toast.LENGTH_SHORT).show();
//        TrueFalseFragment.binding.scoreTrue.setText(true_false + "");

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
//                                score = sp.getInt("TueFalsePoint", point);
//                                Log.d("TueFalsePoint", point + "");

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
//                                }.start()
                                break;

                            case 2:
                                fragments.add(ChooseFragment.newInstance(title, answer1, answer2, answer3, answer4, true_answer, hint, duration, point));
                                break;

                            case 3:
                                fragments.add(FillFragment.newInstance(title, true_answer, hint, duration, point));
                                break;
                        }
                        binding.LevelScore.setText(SplashActivity.sp.getInt(Score, 0)+"");

                        levelAdapterFragment = new LevelAdapterFragment(LevelActivity.this, fragments);
                        binding.LevelPager.setAdapter(levelAdapterFragment);
                    }
                }
            }
        });
        binding.LevelPager.setUserInputEnabled(false);

//        binding.LevelScore.setText("score " + score);
        binding.LevelNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               int Log1= binding.LevelPager.getCurrentItem();
               if (Log1 != 2){
               Log.d("LevelActivity",Log1+"");
                binding.LevelPager.setCurrentItem(Log1+1, true);
                levelAdapterFragment.notifyItemChanged(1);

            }else {
                   startActivity(new Intent(LevelActivity.this,StartPlayingActivity.class));
               }
            }
        });
//        binding.LevelSkip.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FragmentManager fragmentManager =getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManage.
//                fragmentTransaction.replace(R.id.LevelPager,fragments.get(1));
//                fragmentTransaction.commit();
//            }
//        });
    //TODO: How we can refresh shared.
    }
    @Override
    public void onClick(int position) {
        binding.LevelScore.setText(position+"");
    }
}