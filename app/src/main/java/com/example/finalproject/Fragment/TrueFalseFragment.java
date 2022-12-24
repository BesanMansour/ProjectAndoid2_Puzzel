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
//        Intent intent = getActivity().getIntent();
//        int pos = intent.getIntExtra("position", 0);
//
//        ViewModel viewModel = new ViewModelProvider(this).get(ViewModel.class);
//
//        String assets = ParsJson.readFromAssets(getContext(), "json/jsonStr.json");
//        ParsJson p = new ParsJson(getContext());
//        p.readJson(assets);
//
//        fragments = new ArrayList<>();
//        LevelAdapterFragment levelAdapterFragment = new LevelAdapterFragment(getActivity(), fragments);
//
//        viewModel.AllMystery().observe(getActivity(),new Observer<List<Mystery>>(){
//            @Override
//            public void onChanged(List<Mystery> mysteries) {
//                try {
//                    String title = mysteries.get(pos).getTitle();
//                    String true_answer = mysteries.get(pos).getTrue_answer();
//                    String answer1 = mysteries.get(pos).getAnswer_1();
//                    String answer2 = mysteries.get(pos).getAnswer_2();
//                    String answer3 = mysteries.get(pos).getAnswer_3();
//                    String answer4 = mysteries.get(pos).getAnswer_4();
//
//                    fragments.add(ChooseFragment.newInstance(title, answer1, answer2, answer3, answer4, true_answer));
//                    fragments.add(FillFragment.newInstance(title));
//
////                    binding.LevelPager.setAdapter(levelAdapterFragment);
////                    binding.LevelPager.setCurrentItem(1,true);
////                    binding.LevelPager.setCurrentItem(2,true);
////                    binding.LevelPager.setCurrentItem(3,true);
////                    binding.LevelPager.setCurrentItem((int) levelAdapterFragment.getItemId(pos));
//                    levelAdapterFragment.notifyDataSetChanged();
//                }catch (Exception e){
//                    e.printStackTrace();
//                    Log.d("errorMessage",e.getMessage());
//                }
//            }
//        });
        return binding.getRoot();
    }
}