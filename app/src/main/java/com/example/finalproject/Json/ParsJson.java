package com.example.finalproject.Json;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import RoomDatabase.Level;
import RoomDatabase.Mystery;
import RoomDatabase.ViewModel;

public class ParsJson {
    Context context;

    public ParsJson(Context context) {
        this.context = context;
    }

    public void readJson(String assets) {
        ViewModel viewModel = new ViewModelProvider((ViewModelStoreOwner)context).get(ViewModel.class);

        Mystery mystery;
        Level level;
        try {
            JSONArray jsonArray = new JSONArray(assets);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject levelJsonObject = jsonArray.getJSONObject(i);
                int level_no = levelJsonObject.getInt("level_no");
                int unlock_points = levelJsonObject.getInt("unlock_points");

                level = new Level(level_no, unlock_points);
                viewModel.InsertLevel(level);

                JSONArray mysteryJsonArray = levelJsonObject.getJSONArray("questions");
                for (int j = 0; j < mysteryJsonArray.length(); j++) {
                    JSONObject mysteryJsonObject = mysteryJsonArray.getJSONObject(j);
                    int id = mysteryJsonObject.getInt("id");
                    String title = mysteryJsonObject.getString("title");
                    String answer_1 = mysteryJsonObject.getString("answer_1");
                    String answer_2 = mysteryJsonObject.getString("answer_2");
                    String answer_3 = mysteryJsonObject.getString("answer_3");
                    String answer_4 = mysteryJsonObject.getString("answer_4");
                    String true_answer = mysteryJsonObject.getString("true_answer");
                    int points = mysteryJsonObject.getInt("points");
                    int duration = mysteryJsonObject.getInt("duration");
                    String hint = mysteryJsonObject.getString("hint");
                    JSONObject patternJsonObject = mysteryJsonObject.getJSONObject("pattern");
                    int pattern_id = patternJsonObject.getInt("pattern_id");
                    String pattern_name = patternJsonObject.getString("pattern_name");

                    mystery = new Mystery(id,title, answer_1, answer_2, answer_3, answer_4, true_answer
                            , points, level_no, duration,pattern_id,pattern_name, hint);
                    viewModel.InsertMystery(mystery);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public static String readFromAssets(Context context, String fileName) {
        String string = "";
        try {
            InputStream inputStream = context.getAssets().open(fileName);
            int size = inputStream.available();
            byte[] byteObject = new byte[size];
            inputStream.read(byteObject);
            inputStream.close();
            string = new String(byteObject, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return string;
    }
}









//,
//        {
//        "id": 4,
//        "title": "عدد كواكب المجموعة الشمسية",
//        "answer_1": "10 كواكب",
//        "answer_2": "3 كواكب",
//        "answer_3": "8 كواكب",
//        "answer_4": "12 كوكب",
//        "true_answer": "8 كواكب",
//        "points": 2,
//        "duration": 1000,
//        "pattern": {
//        "pattern_id": 2,
//        "pattern_name": "Choose the correct answer"
//        },
//        "hint": "عدد كواكب المجموعة الشمسية هو 8 كواكب فقط"
//        }
