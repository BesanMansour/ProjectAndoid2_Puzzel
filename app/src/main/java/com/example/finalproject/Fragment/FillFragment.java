package com.example.finalproject.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.finalproject.R;
import com.example.finalproject.databinding.FragmentFillBinding;
import com.example.finalproject.modle.MyDialog;

public class FillFragment extends Fragment {

    private static final String ARG_TITLE = "param1";
    private static final String ARG_Answer = "param2";

    private String title;
    public static String answer;

    public FillFragment() {
    }

    public static FillFragment newInstance(String title, String answer) {
        FillFragment fragment = new FillFragment();
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
        FragmentFillBinding binding = FragmentFillBinding.inflate(inflater, container, false);
        binding.FillTitle.setText(title);
//        boolean trueAnswer = answer.equals(binding.FillAnswer.getText().toString());
        binding.CheckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    MyDialog myDialog = MyDialog.newInstanceDialogFill(answer);
                    myDialog.show(getActivity().getSupportFragmentManager(), "dialogTrue");
            }
        });
//        MyDialog myDialog = null;
//        if (binding.FillAnswer.getText().toString() == null){
//            myDialog.dismiss();
//        }else{
//
//        }
        return binding.getRoot();
    }
}