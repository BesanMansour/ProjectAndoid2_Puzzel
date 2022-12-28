package com.example.finalproject.modle;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.finalproject.Fragment.FillFragment;
import com.example.finalproject.Fragment.TrueFalseFragment;
import com.example.finalproject.databinding.DialogBinding;

public class MyDialog extends DialogFragment {
    private static final String ARG_DIALOG = "answerBool";
    private static final String ARG_TRUE_ANSWER = "trueAnswer";


    private static final String ARG_HINT = "hint";

    private boolean answerBool;
    private String true_answer;

    private String hint;

    public MyDialog() {
    }
    public static MyDialog newInstanceDialog(boolean answerBool, String true_answer, String hint) {
        Bundle args = new Bundle();
        MyDialog fragment = new MyDialog();
        args.putBoolean(ARG_DIALOG, answerBool);
        args.putString(ARG_TRUE_ANSWER, true_answer);
        args.putString(ARG_HINT, hint);
        fragment.setArguments(args);
        return fragment;
    }

    public static MyDialog newInstanceDialogTrue(String hint) {
        Bundle args = new Bundle();
        MyDialog fragment = new MyDialog();
//        args.putBoolean(ARG_DIALOG, answerBool);
        args.putString(ARG_HINT, hint);
//        args.putString(ARG_TRUE_ANSWER, true_answer);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            answerBool = getArguments().getBoolean(ARG_DIALOG);
            true_answer = getArguments().getString(ARG_TRUE_ANSWER);
            hint = getArguments().getString(ARG_HINT);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        DialogBinding dialogBinding = DialogBinding.inflate(inflater, container, false);
        dialogBinding.dialogAnswer.setText(hint);
        return dialogBinding.getRoot();
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