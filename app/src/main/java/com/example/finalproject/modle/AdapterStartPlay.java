package com.example.finalproject.modle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.Activity.LevelActivity;
import com.example.finalproject.Activity.SplashActivity;
import com.example.finalproject.databinding.ItemStartPlayingBinding;

import java.util.List;

import RoomDatabase.Level;

public class AdapterStartPlay extends RecyclerView.Adapter<AdapterStartPlay.GameViewHolder> {
    List<Level> levelArrayList;
    Context context;
    MyListener listener;


    public AdapterStartPlay(Context context, List<Level> levelArrayList,MyListener listener) {
        this.context = context;
        this.levelArrayList = levelArrayList;
        this.listener = listener;
    }
    @NonNull
    @Override
    public GameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemStartPlayingBinding binding = ItemStartPlayingBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        return new GameViewHolder(binding);
    }
    @Override
    public void onBindViewHolder(@NonNull GameViewHolder holder, int position) {
        int pos = position;
        int score = SplashActivity.sp.getInt(LevelActivity.Score,0);
        Level level = levelArrayList.get(holder.getAdapterPosition());
        holder.level_no.setText("المجموعة "+ level.getId());

        holder.unlock.setText(String.valueOf(level.getCountPoint()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if(score >= levelArrayList.get(holder.getAdapterPosition()).getCountPoint()){
                    listener.onClick(level.getId());
                    }else {
            Toast.makeText(context, "must br have a same scour or highest", Toast.LENGTH_SHORT).show();
        }}});

    }
    @Override
    public int getItemCount() {
        return levelArrayList.size();
    }

    class GameViewHolder extends RecyclerView.ViewHolder {

        TextView level_no, count_ques ,unlock;

        public GameViewHolder(ItemStartPlayingBinding binding) {
            super(binding.getRoot());

           level_no = binding.ItemLevelNo;
           count_ques = binding.ItemCountQues;
           unlock = binding.ItemUnlock;
        }
    }
}