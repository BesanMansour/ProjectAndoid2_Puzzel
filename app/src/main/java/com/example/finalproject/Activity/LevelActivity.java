package com.example.finalproject.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
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

import RoomDatabase.Level;
import RoomDatabase.Mystery;
import RoomDatabase.ViewModel;

public class LevelActivity extends AppCompatActivity {
    public static ActivityLevelBinding binding;
    public static ArrayList<Fragment> fragments;
    public static int score;
    Thread thread;
    public  static MediaPlayer media_fail;
    public  static MediaPlayer media_win;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLevelBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        media_fail = MediaPlayer.create(this, R.raw.fail_sound);
        media_win = MediaPlayer.create(this, R.raw.win_sound);

        fragments = new ArrayList<>();

        Intent intent = getIntent();
        int pos = intent.getIntExtra("position", 0);

        ViewModel viewModel = new ViewModelProvider(this).get(ViewModel.class);

        String assets = ParsJson.readFromAssets(getApplicationContext(), "json/jsonStr.json");
        ParsJson p = new ParsJson(this);
        p.readJson(assets);

        binding.LevelTV.setText("المجموعة " + pos);

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
//                                thread = new Thread() {
//                                    @Override
//                                    public void run() {
//                                        try {
//                                            sleep(duration);
//                                            fragments.set(1,ChooseFragment.newInstance(title, answer1, answer2, answer3, answer4, true_answer, hint, duration, point));
//                                            binding.LevelPager.setCurrentItem(1,true);
////                                            Intent intent = new Intent(getBaseContext(), HomeActivity.class);
////                                            startActivity(intent);
////                                            finish();
//                                        } catch (InterruptedException e) {
//                                            e.printStackTrace();
//                                        }
//                                    }
//                                };
//                                thread.start();
                                break;

                            case 2:
                                fragments.add(ChooseFragment.newInstance(title, answer1, answer2, answer3, answer4, true_answer, hint, duration, point));
//                                thread = new Thread() {
//                                    @Override
//                                    public void run() {
//                                        try {
//                                            sleep(duration);
//                                            fragments.set(2,FillFragment.newInstance(title, true_answer, hint, duration, point));
//                                            binding.LevelPager.setCurrentItem(2,true);
//                                        } catch (InterruptedException e) {
//                                            e.printStackTrace();
//                                        }
//                                    }
//                                };
//                                thread.start();
                                break;

                            case 3:
                                fragments.add(FillFragment.newInstance(title, true_answer, hint, duration, point));
//                                thread = new Thread() {
//                                    @Override
//                                    public void run() {
//                                        try {
//                                            sleep(duration);
//                                            fragments.set(0,TrueFalseFragment.newInstance(title, true_answer, hint, duration, point));
//                                            binding.LevelPager.setCurrentItem(0,true);
//                                            Intent intent = new Intent(getBaseContext(), StartPlayingActivity.class);
//                                            startActivity(intent);
//                                            finish();
//                                        } catch (InterruptedException e) {
//                                            e.printStackTrace();
//                                        }
//                                    }
//                                };
//                                thread.start();
                                break;
                        }
                        LevelAdapterFragment levelAdapterFragment = new LevelAdapterFragment(LevelActivity.this, fragments);
                        binding.LevelPager.setAdapter(levelAdapterFragment);
                    }
                }
                score = TrueFalseFragment.point + ChooseFragment.point + FillFragment.point;
                binding.LevelNumPoint.setText(score+"");

                Log.d("score",String.valueOf(score));
                Log.d("TrueFalseFragment.point",String.valueOf(TrueFalseFragment.point));
                Log.d("FillFragment.point",String.valueOf(FillFragment.point));
                Log.d("ChooseFragment.point",String.valueOf(ChooseFragment.point));
            }
        });
    }

}