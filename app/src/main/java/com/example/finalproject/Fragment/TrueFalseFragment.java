package com.example.finalproject.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.finalproject.Activity.LevelActivity;
import com.example.finalproject.Activity.SplashActivity;
import com.example.finalproject.Activity.StartPlayingActivity;
import com.example.finalproject.R;
import com.example.finalproject.databinding.ActivityLevelBinding;
import com.example.finalproject.databinding.FragmentTrueFalseBinding;
import com.example.finalproject.modle.MyDialog;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class TrueFalseFragment extends Fragment {
    public interface trueScore {
        void TFQ(int score);
    }
    private static final String ARG_TITLE = "title";
    private static final String ARG_Answer = "answer";
    private static final String ARG_HINT = "hint";
    private static final String ARG_DURATION = "duration";
    private static final String ARG_POINT = "point";

    private String title;
    private String answer;
    private String hint;
    private int duration;
    public static int point;
    trueScore trueScore;


    public TrueFalseFragment() {
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        trueScore = (TrueFalseFragment.trueScore) context;
    }

    public static TrueFalseFragment newInstance(String title, String answer, String hint, int duration, int point) {
        TrueFalseFragment fragment = new TrueFalseFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        args.putString(ARG_Answer, answer);
        args.putString(ARG_HINT, hint);
        args.putInt(ARG_DURATION, duration);
        args.putInt(ARG_POINT, point);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString(ARG_TITLE);
            answer = getArguments().getString(ARG_Answer);
            hint = getArguments().getString(ARG_HINT);
            duration = getArguments().getInt(ARG_DURATION);
            point = getArguments().getInt(ARG_POINT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentTrueFalseBinding binding = FragmentTrueFalseBinding.inflate(inflater, container, false);
        binding.FragTitle.setText(title);

        new CountDownTimer(duration, 1000) {
            @Override
            public void onTick(long l) {
                NumberFormat f = new DecimalFormat("00");
                long hour = (l / 3600000) % 24;
                long min = (l / 60000) % 60;
                long sec = (l / 1000) % 60;
                binding.timer.setText(f.format(hour) + ":" + f.format(min) + ":" + f.format(sec));

                binding.RadioFalse.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (binding.RadioFalse.getText().toString().equals(answer)) {
                            LevelActivity.media_win.start();
                            MyDialog myDialog = MyDialog.newInstanceDialogTrue("Good"+"👏👏", point);
                            myDialog.show(getParentFragmentManager(), "dialogTrue");

                            LevelActivity.CountTrue += 1;
                            SplashActivity.editor.putInt(LevelActivity.CountTQus,LevelActivity.CountTrue);
                            SplashActivity.editor.putInt("point",point);
                            SplashActivity.editor.apply();
                            Log.d("CountTrue",LevelActivity.CountTrue+"");
//                            int sore = SplashActivity.sp.getInt(LevelActivity.Score, 0);
//                            Log.d("score",sore+""); //0
//                            SplashActivity.editor.putInt(LevelActivity.Score, sore + point);
//                            int TrueAnswer = SplashActivity.sp.getInt(LevelActivity.CountTQus, 0);
//                            Log.d("TrueAnswer",TrueAnswer+"");//0
//                            SplashActivity.editor.putInt(LevelActivity.CountTQus, TrueAnswer + 1);
//                            int CountQ = SplashActivity.sp.getInt(LevelActivity.CountQus, 0);
//                            Log.d("CountQ",CountQ+"");//0
//                            SplashActivity.editor.putInt(LevelActivity.CountQus, CountQ + 1);
//                            SplashActivity.editor.apply();

                            trueScore.TFQ(point);
                        } else {
//                            int FalseAnswer = SplashActivity.sp.getInt(LevelActivity.CountFQus, 0);
//                            int CountQ = SplashActivity.sp.getInt(LevelActivity.CountQus, 0);
//                            SplashActivity.editor.putInt(LevelActivity.CountQus, CountQ + 1);
//                            SplashActivity.editor.putInt(LevelActivity.CountFQus, FalseAnswer + 1);
                            LevelActivity.media_fail.start();
                            MyDialog myDialog = MyDialog.newInstanceDialogTrue("😒🤦‍"+"The Correct Answer is:\n" + hint, 0);
                            myDialog.show(getParentFragmentManager(), "dialogTrue");

                            LevelActivity.CountFalse += 1;
                            SplashActivity.editor.putInt(LevelActivity.CountFQus,LevelActivity.CountFalse);
                            SplashActivity.editor.apply();
                        }
                        LevelActivity.CountQ += 1;
                        SplashActivity.editor.putInt(LevelActivity.CountQus,LevelActivity.CountQ);
                        SplashActivity.editor.apply();
                    }
                });
                binding.RadioTrue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (binding.RadioTrue.getText().toString().equals(answer)) {
                            LevelActivity.media_win.start();
                            MyDialog myDialog = MyDialog.newInstanceDialogTrue("Good", point);
                            myDialog.show(getParentFragmentManager(), "dialogTrue");

                            LevelActivity.CountTrue += 1;
                            SplashActivity.editor.putInt(LevelActivity.CountTQus,LevelActivity.CountTrue);
                            SplashActivity.editor.putInt("point",point);
                            SplashActivity.editor.apply();

//                            binding.scoreTrue.setText(point + "");

//                            int sore = SplashActivity.sp.getInt(LevelActivity.Score, 0);
//                            SplashActivity.editor.putInt(LevelActivity.Score, sore + point);
//                            int TrueAnswer = SplashActivity.sp.getInt(LevelActivity.CountTQus, 0);
//                            SplashActivity.editor.putInt(LevelActivity.CountTQus, TrueAnswer + 1);
//                            int CountQ = SplashActivity.sp.getInt(LevelActivity.CountQus, 0);
//                            SplashActivity.editor.putInt(LevelActivity.CountQus, CountQ + 1);

                            trueScore.TFQ(point);
                        } else {
                            int FalseAnswer = SplashActivity.sp.getInt(LevelActivity.CountFQus, 0);
                            int CountQ = SplashActivity.sp.getInt(LevelActivity.CountQus, 0);
                            SplashActivity.editor.putInt(LevelActivity.CountQus, CountQ + 1);
                            SplashActivity.editor.putInt(LevelActivity.CountFQus, FalseAnswer + 1);
                            LevelActivity.media_fail.start();
                            MyDialog myDialog = MyDialog.newInstanceDialogTrue("😒🤦‍"+"The Correct Answer is:\n" + hint, 0);
                            myDialog.show(getParentFragmentManager(), "dialogTrue");

                            LevelActivity.CountFalse += 1;
                            SplashActivity.editor.putInt(LevelActivity.CountFQus,LevelActivity.CountFalse);
                            SplashActivity.editor.apply();
                        }
                        LevelActivity.CountQ += 1;
                        SplashActivity.editor.putInt(LevelActivity.CountQus,LevelActivity.CountQ);
                        SplashActivity.editor.apply();
                    }
                });
            }

            @Override
            public void onFinish() {
                binding.timer.setText("00:00:00");
                int pager = LevelActivity.binding.LevelPager.getCurrentItem();
                if (pager != 2) {
                    LevelActivity.binding.LevelPager.setCurrentItem(pager + 1, true);
                   LevelActivity.levelAdapterFragment.notifyItemChanged(1);
                } else {
                    startActivity(new Intent(getActivity(), StartPlayingActivity.class));
                }
            }
        }.start();

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        return;
    }
}
