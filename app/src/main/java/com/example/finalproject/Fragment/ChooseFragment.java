package com.example.finalproject.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.finalproject.Activity.LevelActivity;
import com.example.finalproject.Activity.SplashActivity;
import com.example.finalproject.Activity.StartPlayingActivity;
import com.example.finalproject.databinding.FragmentChooseBinding;
import com.example.finalproject.modle.ListenerScore;
import com.example.finalproject.modle.MyDialog;
import com.example.finalproject.modle.MyListener;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class ChooseFragment extends Fragment {
    public interface chooseScore{
        void ChQ(int score);
    }
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_ANSWER1 = "answer1";
    private static final String ARG_ANSWER2 = "answer2";
    private static final String ARG_ANSWER3 = "answer3";
    private static final String ARG_ANSWER4 = "answer4";
    private static final String ARG_TRUE = "true_answer";
    private static final String ARG_HINT = "hint";
    private static final String ARG_DURATION = "duration";
    private static final String ARG_POINT = "point";

    private String mParam1;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private String true_answer;
    private String hint;
    private int duration;
    public static int point;
    chooseScore chooseScore;

    public ChooseFragment() {
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        chooseScore = (ChooseFragment.chooseScore) context;
    }

    public static ChooseFragment newInstance(String param1, String answer1, String answer2, String answer3, String answer4, String true_answer, String hint
            , int duration, int point) {
        ChooseFragment fragment = new ChooseFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_ANSWER1, answer1);
        args.putString(ARG_ANSWER2, answer2);
        args.putString(ARG_ANSWER3, answer3);
        args.putString(ARG_ANSWER4, answer4);
        args.putString(ARG_TRUE, true_answer);
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
            mParam1 = getArguments().getString(ARG_PARAM1);
            answer1 = getArguments().getString(ARG_ANSWER1);
            answer2 = getArguments().getString(ARG_ANSWER2);
            answer3 = getArguments().getString(ARG_ANSWER3);
            answer4 = getArguments().getString(ARG_ANSWER4);
            true_answer = getArguments().getString(ARG_TRUE);
            hint = getArguments().getString(ARG_HINT);
            duration = getArguments().getInt(ARG_DURATION);
            point = getArguments().getInt(ARG_POINT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentChooseBinding binding = FragmentChooseBinding.inflate(getLayoutInflater());
        binding.ChooseTitle.setText(mParam1);
        binding.ChooseAnswer1.setText(answer1);
        binding.ChooseAnswer2.setText(answer2);
        binding.ChooseAnswer3.setText(answer3);
        binding.ChooseAnswer4.setText(answer4);

        boolean answer1 = true_answer.equals(binding.ChooseAnswer1.getText().toString());
        boolean answer2 = true_answer.equals(binding.ChooseAnswer2.getText().toString());
        boolean answer3 = true_answer.equals(binding.ChooseAnswer3.getText().toString());
        boolean answer4 = true_answer.equals(binding.ChooseAnswer4.getText().toString());

//        int TueFalsePoint = LevelActivity.sp.getInt("TueFalsePoint", TrueFalseFragment.point);
//        binding.score.setText(TueFalsePoint + "");

        new CountDownTimer(duration, 1000) {
            @Override
            public void onTick(long l) {
                NumberFormat f = new DecimalFormat("00");
                long hour = (l / 3600000) % 24;
                long min = (l / 60000) % 60;
                long sec = (l / 1000) % 60;
                binding.timer.setText(f.format(hour) + ":" + f.format(min) + ":" + f.format(sec));

                binding.ChooseAnswer1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (answer1) {
                            LevelActivity.media_win.start();

                            MyDialog myDialog = MyDialog.newInstanceDialogTrue("Good", point);
                            myDialog.show(getActivity().getSupportFragmentManager(), "dialogTrue");

//                            binding.score.setText(String.valueOf(TueFalsePoint + point));

//                            int sore = SplashActivity.sp.getInt(LevelActivity.Score, 0);
                            LevelActivity.CountTrue+=1;
                            SplashActivity.editor.putInt(LevelActivity.CountTQus,LevelActivity.CountTrue );
                            SplashActivity.editor.apply();

                            chooseScore.ChQ(point);
                        } else {
                            LevelActivity.media_fail.start();
                            MyDialog myDialog = MyDialog.newInstanceDialogTrue("The Correct Answer is:\n" + hint, 0);
                            myDialog.show(getActivity().getSupportFragmentManager(), "dialogTrue");

                            LevelActivity.CountFalse+=1;
                            SplashActivity.editor.putInt(LevelActivity.CountTQus,LevelActivity.CountFalse );
                            SplashActivity.editor.apply();
                        }
                        LevelActivity.CountQ += 1;
                        SplashActivity.editor.putInt(LevelActivity.CountQus,LevelActivity.CountQ);
                        SplashActivity.editor.apply();
                    }
                });
                binding.ChooseAnswer2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (answer2) {
                            LevelActivity.media_win.start();

                            MyDialog myDialog = MyDialog.newInstanceDialogTrue("Good", point);
                            myDialog.show(getActivity().getSupportFragmentManager(), "dialogTrue");

                            LevelActivity.CountTrue+=1;
                            SplashActivity.editor.putInt(LevelActivity.CountTQus,LevelActivity.CountTrue );
                            SplashActivity.editor.apply();
//                            binding.score.setText(String.valueOf(TueFalsePoint + point));


//                            int sore =SplashActivity.sp.getInt(LevelActivity.Score, 0);
//                            SplashActivity.editor.putInt(LevelActivity.Score, sore+point);
//                            SplashActivity.editor.apply();
                            chooseScore.ChQ(point);
                        } else {
                            LevelActivity.media_fail.start();

                            MyDialog myDialog = MyDialog.newInstanceDialogTrue("The Correct Answer is:\n" + hint, 0);
                            myDialog.show(getActivity().getSupportFragmentManager(), "dialogTrue");

                            LevelActivity.CountFalse+=1;
                            SplashActivity.editor.putInt(LevelActivity.CountTQus,LevelActivity.CountFalse );
                            SplashActivity.editor.apply();
                        }
                        LevelActivity.CountQ += 1;
                        SplashActivity.editor.putInt(LevelActivity.CountQus,LevelActivity.CountQ);
                        SplashActivity.editor.apply();
                    }
                });
                binding.ChooseAnswer3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (answer3) {
                            LevelActivity.media_win.start();
                            MyDialog myDialog = MyDialog.newInstanceDialogTrue("Good", point);
                            myDialog.show(getActivity().getSupportFragmentManager(), "dialogTrue");
//                            binding.score.setText(String.valueOf(TueFalsePoint + point));

//                            int sore =SplashActivity.sp.getInt(LevelActivity.Score, 0);
//                            SplashActivity.editor.putInt(LevelActivity.Score, sore+point);
//                            SplashActivity.editor.apply();
                            LevelActivity.CountTrue+=1;
                            SplashActivity.editor.putInt(LevelActivity.CountTQus,LevelActivity.CountTrue );
                            SplashActivity.editor.apply();
                            chooseScore.ChQ(point);
                        } else {
                            LevelActivity.media_fail.start();

                            MyDialog myDialog = MyDialog.newInstanceDialogTrue("The Correct Answer is:\n" + hint, 0);
                            myDialog.show(getActivity().getSupportFragmentManager(), "dialogTrue");
                            LevelActivity.CountFalse+=1;
                            SplashActivity.editor.putInt(LevelActivity.CountTQus,LevelActivity.CountFalse );
                            SplashActivity.editor.apply();
                        }
                        LevelActivity.CountQ += 1;
                        SplashActivity.editor.putInt(LevelActivity.CountQus,LevelActivity.CountQ);
                        SplashActivity.editor.apply();
                    }
                });
                binding.ChooseAnswer4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (answer4) {
                            LevelActivity.media_win.start();
                            MyDialog myDialog = MyDialog.newInstanceDialogTrue("Good", point);
                            myDialog.show(getActivity().getSupportFragmentManager(), "dialogTrue");
//                            binding.score.setText(String.valueOf(TueFalsePoint + point));

//                            int sore =SplashActivity.sp.getInt(LevelActivity.Score, 0);
//                            SplashActivity.editor.putInt(LevelActivity.Score, sore+point);
//                            SplashActivity.editor.apply();
                            LevelActivity.CountTrue+=1;
                            SplashActivity.editor.putInt(LevelActivity.CountTQus,LevelActivity.CountTrue );
                            SplashActivity.editor.apply();
                            chooseScore.ChQ(point);
                        } else {
                            LevelActivity.media_fail.start();

                            MyDialog myDialog = MyDialog.newInstanceDialogTrue("The Correct Answer is:\n" + hint, 0);
                            myDialog.show(getActivity().getSupportFragmentManager(), "dialogTrue");

                            LevelActivity.CountFalse+=1;
                            SplashActivity.editor.putInt(LevelActivity.CountTQus,LevelActivity.CountFalse );
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