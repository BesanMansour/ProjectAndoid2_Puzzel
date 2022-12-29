package com.example.finalproject.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.finalproject.Activity.HomeActivity;
import com.example.finalproject.Activity.LevelActivity;
import com.example.finalproject.Activity.TrueFalseDialog;
import com.example.finalproject.R;
import com.example.finalproject.databinding.FragmentTrueFalseBinding;
import com.example.finalproject.modle.MyDialog;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.Duration;
import java.util.Timer;
import java.util.TimerTask;

public class TrueFalseFragment extends Fragment {

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

    public TrueFalseFragment() {
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
//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {

        new CountDownTimer(duration, 1000) {
            @Override
            public void onTick(long l) {
                NumberFormat f = new DecimalFormat("00");
                long hour = (l / 3600000) % 24;
                long min = (l / 60000) % 60;
                long sec = (l / 1000) % 60;
                Toast.makeText(getContext(), duration+"", Toast.LENGTH_SHORT).show();
                binding.timer.setText(f.format(hour) + ":" + f.format(min) + ":" + f.format(sec));

                binding.RadioFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.RadioFalse.getText().toString().equals(answer)) {
//                    LevelActivity.score = point;
                    LevelActivity.media_win.start();
                    MyDialog myDialog = MyDialog.newInstanceDialogTrue("Good", point);
                    myDialog.show(getParentFragmentManager(), "dialogTrue");
                } else {
//                    LevelActivity.score = 0;
                    LevelActivity.media_fail.start();
                    MyDialog myDialog = MyDialog.newInstanceDialogTrue("The Correct Answer is:\n" + hint, 0);
                    myDialog.show(getParentFragmentManager(), "dialogTrue");
                }
//                LevelActivity.binding.LevelNumPoint.setText("Score: "+LevelActivity.score);
            }
        });
        binding.RadioTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (binding.RadioTrue.getText().toString().equals(answer)) {
//                    LevelActivity.score = point;
                    LevelActivity.media_win.start();
                    MyDialog myDialog = MyDialog.newInstanceDialogTrue("Good", point);
                    myDialog.show(getParentFragmentManager(), "dialogTrue");
                } else {
//                    LevelActivity.score = 0;
                    LevelActivity.media_fail.start();
                    MyDialog myDialog = MyDialog.newInstanceDialogTrue("try again \n" + hint, 0);
                    myDialog.show(getParentFragmentManager(), "dialogTrue");
                }
//                LevelActivity.binding.LevelNumPoint.setText("Score: "+LevelActivity.score);
            }
        });
//        Thread thread = new Thread() {
//            @Override
//            public void run() {
//                try {
//                    sleep(x);
//                    Intent intent = new Intent(getContext(), HomeActivity.class);
//                    startActivity(intent);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        };
//        thread.start();

//     }
//        },1000);
            }
            @Override
            public void onFinish() {
                binding.timer.setText("00:00:00");
                     Toast.makeText(getContext(), "Finish!", Toast.LENGTH_SHORT).show();
                FragmentManager fragmentManager =getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                fragmentTransaction.replace(R.id.LevelPager,LevelActivity.fragments.get(2));
                fragmentTransaction.commit();
                //Todo: chenge this fragment to next one
            }
        }.start();



        return binding.getRoot();
    }
}
