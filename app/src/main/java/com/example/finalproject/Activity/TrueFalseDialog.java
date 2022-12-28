package com.example.finalproject.Activity;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.finalproject.Fragment.TrueFalseFragment;
import com.example.finalproject.databinding.DialogTrueFalseBinding;
import com.example.finalproject.databinding.FragmentTrueFalseBinding;

public class TrueFalseDialog extends DialogFragment {

    private static final String ARG_ANSWER = "answer";
    private static final String ARG_HINT = "hint";

    private boolean answer;
    private String hint;

    public TrueFalseDialog() {
    }

    public static TrueFalseDialog newInstance(boolean answer, String hint) {
        TrueFalseDialog fragment = new TrueFalseDialog();
        Bundle args = new Bundle();
        args.putBoolean(ARG_ANSWER, answer);
        args.putString(ARG_HINT, hint);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            answer = getArguments().getBoolean(ARG_ANSWER);
            hint = getArguments().getString(ARG_HINT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState) {
        DialogTrueFalseBinding binding = DialogTrueFalseBinding.inflate(inflater, container, false);
        if (answer) {
            binding.dialogTv.setText("Good");
        } else {
            binding.dialogTv.setText("try again");
            binding.dialogAnswer.setText(hint);
        }
        Toast.makeText(getContext(), answer+"", Toast.LENGTH_SHORT).show();
        return binding.getRoot();
    }
    @Override
    public void onResume() {
        super.onResume();
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = RelativeLayout.LayoutParams.MATCH_PARENT;
        params.height = RelativeLayout.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
    }
}