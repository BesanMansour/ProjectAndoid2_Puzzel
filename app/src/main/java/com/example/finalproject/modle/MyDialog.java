package com.example.finalproject.modle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.finalproject.Fragment.FillFragment;
import com.example.finalproject.databinding.DialogBinding;

public class MyDialog extends DialogFragment {
    private static final String ARG_DIALOG = "trueAnswer";
    private static final String ARG_DIALOG_FILL = "trueAnswer";

    private boolean trueAnswer;
    public static String answer;

    public MyDialog() {
    }

    public static MyDialog newInstanceDialog(boolean trueAnswer) {
        Bundle args = new Bundle();
        MyDialog fragment = new MyDialog();
        args.putBoolean(ARG_DIALOG, trueAnswer);
        fragment.setArguments(args);
        return fragment;
    }

    public static MyDialog newInstanceDialogFill(String answer) {
        Bundle args = new Bundle();
        MyDialog fragment = new MyDialog();
        args.putString(ARG_DIALOG_FILL, answer);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            trueAnswer = getArguments().getBoolean(ARG_DIALOG);
            answer = getArguments().getString(ARG_DIALOG_FILL);
        }
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        DialogBinding dialogBinding = DialogBinding.inflate(inflater, container, false);
        if (trueAnswer) {
            dialogBinding.dialogTv.setText("Good");
        }else if (!trueAnswer){
            dialogBinding.dialogTv.setText("try again");
        }
        else if (answer.equals(FillFragment.answer)) {
            dialogBinding.dialogTv.setText("Good");
        }else if (!answer.equals(FillFragment.answer)){
            dialogBinding.dialogTv.setText("try again");
        }
        Toast.makeText(getContext(), answer, Toast.LENGTH_SHORT).show();
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
