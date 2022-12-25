package com.example.finalproject.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.finalproject.Json.ParsJson;
import com.example.finalproject.databinding.FragmentTrueFalseBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import RoomDatabase.Mystery;
import RoomDatabase.ViewModel;

public class TrueFalseFragment extends Fragment {
    private static final String ARG_TITLE = "param1";
    private static final String ARG_Answer = "param2";
//    ArrayList<Fragment> fragments;

    private String title;
    private boolean answer;

    public TrueFalseFragment() {
    }

    public static TrueFalseFragment newInstance(String title,boolean answer) {
        TrueFalseFragment fragment = new TrueFalseFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        args.putBoolean(ARG_Answer, answer);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString(ARG_TITLE);
            answer = getArguments().getBoolean(ARG_Answer);
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