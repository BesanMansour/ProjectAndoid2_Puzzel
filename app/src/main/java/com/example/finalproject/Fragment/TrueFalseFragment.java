package com.example.finalproject.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.finalproject.databinding.FragmentTrueFalseBinding;

public class TrueFalseFragment extends Fragment {

    private static final String ARG_TITLE = "param1";
    private static final String ARG_Answer = "param2";

    private String title;
    private String answer;

    public TrueFalseFragment() {
    }

    public static TrueFalseFragment newInstance(String title,String answer) {
        TrueFalseFragment fragment = new TrueFalseFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        args.putString(ARG_Answer, answer);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString(ARG_TITLE);
            answer = getArguments().getString(ARG_Answer);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentTrueFalseBinding binding = FragmentTrueFalseBinding.inflate(inflater,container,false);
        binding.FragTitle.setText(title);
        return binding.getRoot();
    }
}