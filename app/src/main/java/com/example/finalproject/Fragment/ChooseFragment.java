package com.example.finalproject.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.finalproject.databinding.FragmentChooseBinding;

public class ChooseFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_ANSWER1 = "answer1";
    private static final String ARG_ANSWER2 = "answer2";
    private static final String ARG_ANSWER3 = "answer3";
    private static final String ARG_ANSWER4 = "answer4";
    private static final String ARG_TRUE = "true_answer";

    private String mParam1;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private String true_answer;

    public ChooseFragment() {
    }
    public static ChooseFragment newInstance(String param1,String answer1,String answer2,String answer3,String answer4,String true_answer) {
        ChooseFragment fragment = new ChooseFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_ANSWER1, answer1);
        args.putString(ARG_ANSWER2, answer2);
        args.putString(ARG_ANSWER3, answer3);
        args.putString(ARG_ANSWER4, answer4);
        args.putString(ARG_TRUE, true_answer);
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
        return binding.getRoot();
    }
}