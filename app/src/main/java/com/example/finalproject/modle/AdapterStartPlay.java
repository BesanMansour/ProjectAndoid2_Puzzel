package com.example.finalproject.modle;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
    int totalScore=0;

    public AdapterStartPlay(Context context, List<Level> levelArrayList, MyListener listener) {
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
        Level level = levelArrayList.get(holder.getAdapterPosition());
        holder.level_no.setText("المجموعة " + level.getId());

        holder.count_score.setText(level.getCountPoint()+"\n نقاط");
        if (level.getId()==1){
            holder.unlock.setVisibility(View.VISIBLE);
            holder.lock.setVisibility(View.GONE);
        }
        else{
            holder.unlock.setVisibility(View.GONE);
            holder.lock.setVisibility(View.VISIBLE);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int score = SplashActivity.sp.getInt("levelScore", 0);

                Toast.makeText(context, "score"+score, Toast.LENGTH_SHORT).show();//5
                Toast.makeText(context, "countPoint"+levelArrayList.get(holder.getAdapterPosition()).getCountPoint(), Toast.LENGTH_SHORT).show();//6
                Log.d("countPoint",levelArrayList.get(holder.getAdapterPosition()).getCountPoint()+"");
                if (score >= levelArrayList.get(holder.getAdapterPosition()).getCountPoint()) {
                    listener.onClick(level.getId());
                    holder.lock.setVisibility(View.GONE);
                    holder.unlock.setVisibility(View.VISIBLE);
                } else {
                    holder.lock.setVisibility(View.VISIBLE);
                    holder.unlock.setVisibility(View.GONE);
                    Toast.makeText(context, "must br have a same scour or highest", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return levelArrayList.size();
    }

    class GameViewHolder extends RecyclerView.ViewHolder {

        TextView level_no, count_score;
        ImageView lock,unlock;

        public GameViewHolder(ItemStartPlayingBinding binding) {
            super(binding.getRoot());

            level_no = binding.ItemLevelNo;
            count_score = binding.ItemCountScore;
            lock = binding.ItemImgLock;
            unlock = binding.ItemImgUnLock;
        }
    }
}