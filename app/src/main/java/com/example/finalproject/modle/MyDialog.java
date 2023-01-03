package com.example.finalproject.modle;

import android.content.Intent;
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

import com.example.finalproject.Activity.LevelActivity;
import com.example.finalproject.Activity.StartPlayingActivity;
import com.example.finalproject.Fragment.FillFragment;
import com.example.finalproject.Fragment.TrueFalseFragment;
import com.example.finalproject.databinding.DialogBinding;

public class MyDialog extends DialogFragment {
    private static final String ARG_HINT = "hint";
    private static final String ARG_POINT = "point";
    private static final String ARG_IMAGE = "image";

    private String hint;
    private int point;
    private int image;

    public MyDialog() {
    }
    public static MyDialog newInstanceDialogTrue(String hint,int point,int image) {
        Bundle args = new Bundle();
        MyDialog fragment = new MyDialog();
        args.putString(ARG_HINT, hint);
        args.putInt(ARG_POINT, point);
        args.putInt(ARG_IMAGE, image);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            hint = getArguments().getString(ARG_HINT);
            point = getArguments().getInt(ARG_POINT);
            image = getArguments().getInt(ARG_IMAGE);
        }
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        DialogBinding dialogBinding = DialogBinding.inflate(inflater, container, false);
        dialogBinding.dialogAnswer.setText(hint);
        dialogBinding.DialogImg.setImageResource(image);
        dialogBinding.dialogNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pager = LevelActivity.binding.LevelPager.getCurrentItem();
                if (pager != 2) {
                    LevelActivity.binding.LevelPager.setCurrentItem(pager + 1, true);
                    LevelActivity.levelAdapterFragment.notifyItemChanged(1);
                } else {
                    startActivity(new Intent(getActivity(), StartPlayingActivity.class));
                }
                dismiss();
            }
        });
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