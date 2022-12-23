package com.example.finalproject.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.finalproject.R;
import com.example.finalproject.databinding.FragmentFillBinding;

public class FillFragment extends Fragment {

    private static final String ARG_TITLE = "param1";
    private static final String ARG_Answer = "param2";

    private String title;
//    private String answer;

    public FillFragment() {
    }
    public static FillFragment newInstance(String title) {
        FillFragment fragment = new FillFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
//        args.putString(ARG_Answer, answer);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString(ARG_TITLE);
//            answer = getArguments().getString(ARG_Answer);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentFillBinding binding = FragmentFillBinding.inflate(inflater,container,false);
        binding.FillTitle.setText(title);
//        binding.FillAnswer.setText(answer);
        return binding.getRoot();
    }
}