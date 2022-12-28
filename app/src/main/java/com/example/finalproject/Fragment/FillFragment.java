package com.example.finalproject.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.finalproject.R;
import com.example.finalproject.databinding.FragmentFillBinding;
import com.example.finalproject.modle.MyDialog;

public class FillFragment extends Fragment {

    private static final String ARG_TITLE = "title";
    private static final String ARG_Answer = "answer";
    private static final String ARG_HINT = "hint";
    private static final String ARG_DURATION = "duration";
    private static final String ARG_POINT = "point";

    private String title;
    private String answerFill;
    String hint;
    private int duration;
    private int point;

    public FillFragment() {
    }

    public static FillFragment newInstance(String title, String answerFill,String hint,int duration,int point) {
        FillFragment fragment = new FillFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        args.putString(ARG_Answer, answerFill);
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
            title = getArguments().getString(ARG_TITLE);
            answerFill = getArguments().getString(ARG_Answer);
            hint = getArguments().getString(ARG_HINT);
            duration = getArguments().getInt(ARG_DURATION);
            point = getArguments().getInt(ARG_POINT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentFillBinding binding = FragmentFillBinding.inflate(inflater, container, false);
        binding.FillTitle.setText(title);
        Log.d("answerFill ",answerFill); // المريخ
        binding.CheckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.FillAnswer.getText().toString().equals(answerFill)){
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