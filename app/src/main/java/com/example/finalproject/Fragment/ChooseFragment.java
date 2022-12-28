package com.example.finalproject.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.finalproject.databinding.FragmentChooseBinding;
import com.example.finalproject.modle.MyDialog;

public class ChooseFragment extends Fragment {

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
    private String hint ;
    private int duration;
    private int point;

    public ChooseFragment() {
    }

    public static ChooseFragment newInstance(String param1, String answer1, String answer2, String answer3, String answer4, String true_answer,String hint
    ,int duration,int point) {
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

        binding.ChooseAnswer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("ChooseAnswer1 ",String.valueOf(answer1)); // true
                Log.d("ChooseTrueAnswer ",true_answer); // أديس بابا
                if (answer1) {
                    MyDialog myDialog = MyDialog.newInstanceDialogTrue("Good");
                    myDialog.show(getActivity().getSupportFragmentManager(), "dialogTrue");
                }else{
                    MyDialog myDialog = MyDialog.newInstanceDialogTrue("The Correct Answer is:\n"+hint);
                    myDialog.show(getActivity().getSupportFragmentManager(), "dialogTrue");
                }
            }
        });
        binding.ChooseAnswer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("ChooseAnswer2 ",String.valueOf(answer2)); //false
                Log.d("ChooseTrueAnswer ",true_answer); // أديس ابابا
                if (answer2) {
                    MyDialog myDialog = MyDialog.newInstanceDialogTrue("Good");
                    myDialog.show(getActivity().getSupportFragmentManager(), "dialogTrue");
                }else{
                    MyDialog myDialog = MyDialog.newInstanceDialogTrue("The Correct Answer is:\n"+hint);
                    myDialog.show(getActivity().getSupportFragmentManager(), "dialogTrue");
                }
            }
        });
        binding.ChooseAnswer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (answer3) {
                    MyDialog myDialog = MyDialog.newInstanceDialogTrue("Good");
                    myDialog.show(getActivity().getSupportFragmentManager(), "dialogTrue");
                }else{
                    MyDialog myDialog = MyDialog.newInstanceDialogTrue("The Correct Answer is:\n"+hint);
                    myDialog.show(getActivity().getSupportFragmentManager(), "dialogTrue");
                }
            }
        });
        binding.ChooseAnswer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (answer4) {
                    MyDialog myDialog = MyDialog.newInstanceDialogTrue("Good");
                    myDialog.show(getActivity().getSupportFragmentManager(), "dialogTrue");
                }else{
                    MyDialog myDialog = MyDialog.newInstanceDialogTrue("The Correct Answer is:\n"+hint);
                    myDialog.show(getActivity().getSupportFragmentManager(), "dialogTrue");
                }
            }
        });
        return binding.getRoot();
    }
}