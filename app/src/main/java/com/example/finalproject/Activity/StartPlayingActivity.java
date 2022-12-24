package com.example.finalproject.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.finalproject.Json.ParsJson;
import com.example.finalproject.databinding.ActivityStartPlayingBinding;
import com.example.finalproject.modle.AdapterStartPlay;
import com.example.finalproject.modle.MyListener;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import RoomDatabase.Level;
import RoomDatabase.ViewModel;

public class StartPlayingActivity extends AppCompatActivity {
    ActivityStartPlayingBinding binding;
    List<Level> level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStartPlayingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewModel viewModel = new ViewModelProvider(this).get(ViewModel.class);

        String assets = ParsJson.readFromAssets(getApplicationContext(),"json/jsonStr.json");

        ParsJson p = new ParsJson(this);
        p.readJson(assets);

        level= new ArrayList<>();
        viewModel.AllLevel().observe(this, new Observer<List<Level>>() {
            @Override
            public void onChanged(List<Level> levels) {
                level = levels;
                AdapterStartPlay adapterStartPlay = new AdapterStartPlay(getApplicationContext(),level, new MyListener() {
                    @Override
                    public void onClick(int position) {
                        Intent intent = new Intent(getBaseContext(), LevelActivity.class);
                        intent.putExtra("position",position);
                        startActivity(intent);
                    }
                });
                binding.StartPlayingRecycler.setAdapter(adapterStartPlay);
                binding.StartPlayingRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                        RecyclerView.VERTICAL,false));
                adapterStartPlay.notifyDataSetChanged();
            }
        });
    }
    //    private void parsJson(String assets){
//        try {
//            JSONArray jsonArray = new JSONArray(assets);
//            ArrayList<game> gameArrayList = new ArrayList<>();
//            for (int i = 0; i < jsonArray.length(); i++) {
//                JSONObject gameJsonObject = new JSONObject(jsonArray.get(i).toString());
//                int level_no = gameJsonObject.getInt("level_no");
//                int unlock_points = gameJsonObject.getInt("unlock_points");
//
//                ArrayList<questions> questionsArrayList = new ArrayList<>();
//
//                game game = new game();
//                game.setLevel_no(level_no);
//                game.setUnlock_points(unlock_points);
//                game.setQuestionsArrayList(questionsArrayList);
//                gameArrayList.add(game);
//
//                AdapterStartPlay adapter = new AdapterStartPlay(getBaseContext(), gameArrayList, new AdapterStartPlay.MyListener() {
//                    @Override
//                    public void onClick(int position) {
//                        Intent intent = new Intent(getBaseContext(), LevelActivity.class);
//                        intent.putExtra("position",position);
//                        startActivity(intent);
//                    }
//                });
//                binding.StartPlayingRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));
//                binding.StartPlayingRecycler.setAdapter(adapter);
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }

    private String readFromAssets(String fileName) {
        String string = "";
        try {
            InputStream inputStream = getAssets().open(fileName);
            int size = inputStream.available();
            byte[] byteObject = new byte[size];
            inputStream.read(byteObject);
            inputStream.close();
            string = new String(byteObject,"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return string;
    }
}