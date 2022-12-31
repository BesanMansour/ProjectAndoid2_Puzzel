package com.example.finalproject.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.finalproject.Activity.LevelActivity;
import com.example.finalproject.Activity.SplashActivity;
import com.example.finalproject.R;
import com.example.finalproject.databinding.FragmentFillBinding;
import com.example.finalproject.modle.MyDialog;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class FillFragment extends Fragment {

    private static final String ARG_TITLE = "title";
    private static final String ARG_Answer = "answer";
    private static final String ARG_HINT = "hint";
    private static final String ARG_DURATION = "duration";
    private static final String ARG_POINT = "point";

    private String title;
    private String answerFill;
    String hint;
    private int duration;
    public static int point;

    public FillFragment() {
    }

    public static FillFragment newInstance(String title, String answerFill, String hint, int duration, int point) {
        FillFragment fragment = new FillFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        args.putString(ARG_Answer, answerFill);
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
            answerFill = getArguments().getString(ARG_Answer);
            hint = getArguments().getString(ARG_HINT);
            duration = getArguments().getInt(ARG_DURATION);
            point = getArguments().getInt(ARG_POINT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentFillBinding binding = FragmentFillBinding.inflate(inflater, container, false);
        binding.FillTitle.setText(title);

        int TueFalsePoint= SplashActivity.sp.getInt("TueFalsePoint",TrueFalseFragment.point);
        int ChoosePoint= SplashActivity.sp.getInt("ChoosePoint",ChooseFragment.point);
        binding.score.setText(String.valueOf(TueFalsePoint+ChoosePoint));

        new CountDownTimer(duration, 1000) {
            @Override
            public void onTick(long l) {
                NumberFormat f = new DecimalFormat("00");
                long hour = (l / 3600000) % 24;
                long min = (l / 60000) % 60;
                long sec = (l / 1000) % 60;
                binding.timer.setText(f.format(hour) + ":" + f.format(min) + ":" + f.format(sec));

                binding.CheckBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (binding.FillAnswer.getText().toString().equals(answerFill)) {
                            LevelActivity.media_win.start();
                            MyDialog myDialog = MyDialog.newInstanceDialogTrue("Good", point);
                            myDialog.show(getActivity().getSupportFragmentManager(), "dialogTrue");

                            binding.score.setText(String.valueOf(TueFalsePoint + ChoosePoint + point));

                            int sore = SplashActivity.sp.getInt(LevelActivity.Score, 0);
                            SplashActivity.editor.putInt(LevelActivity.Score, sore+point);
                            SplashActivity.editor.apply();
                        } else {
                            LevelActivity.media_fail.start();
                            MyDialog myDialog = MyDialog.newInstanceDialogTrue("The Correct Answer is:\n" + hint, 0);
                            myDialog.show(getActivity().getSupportFragmentManager(), "dialogTrue");
                        }

                    }
                });
            }

            @Override
            public void onFinish() {
                binding.timer.setText("00:00:00");
                //Todo: chenge this fragment to next one
            }
        }.start();
        return binding.getRoot();
    }
}