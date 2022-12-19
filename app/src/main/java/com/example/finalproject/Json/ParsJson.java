package com.example.finalproject.Json;

import android.content.Context;
import android.media.MediaCodec;

import androidx.room.Room;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import RoomDatabase.Level;
import RoomDatabase.MyRoomDatabase;
import RoomDatabase.Mystery;
import RoomDatabase.Pattern;

public class ParsJson {

    public static void readJson(String assets) {
        Context context = null;
        MyRoomDatabase roomDatabase = Room.databaseBuilder(context.getApplicationContext(),
                MyRoomDatabase.class, "database").allowMainThreadQueries().build();
        try {
            JSONArray jsonArray = new JSONArray(assets);
            ArrayList<Level> levelArrayList = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject levelJsonObject = new JSONObject(jsonArray.get(i).toString());
                int level_no = levelJsonObject.getInt("level_no");
                int unlock_points = levelJsonObject.getInt("unlock_points");

                JSONArray mysteryJsonArray = levelJsonObject.getJSONArray("questions");
                ArrayList<Mystery> mysteryArrayList = new ArrayList<>();
                for (int j = 0; j < mysteryJsonArray.length(); j++) {
                    JSONObject mysteryJsonObject = new JSONObject(mysteryJsonArray.get(i).toString());
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

                    Pattern pattern = new Pattern(pattern_name);

                    Mystery mystery = new Mystery(title,answer_1,answer_2,answer_3,answer_4,true_answer
                    ,points,pattern_id,duration,pattern,hint);

//                    pattern.setPattern_id(pattern_id);
//                    pattern.setPattern_name(pattern_name);

//                    questions.setId(id);
//                    questions.setTitle(title);
//                    questions.setAnswer_1(answer_1);
//                    questions.setAnswer_2(answer_2);
//                    questions.setAnswer_3(answer_3);
//                    questions.setAnswer_4(answer_4);
//                    questions.setTrue_answer(true_answer);
//                    questions.setPoints(points);
//                    questions.setDuration(duration);
//                    questions.setHint(hint);
//                    questions.setPattern(pattern);
//
//                    questionsArrayList.add(questions);

                    roomDatabase.mysteryDao().InsertMystery(mystery);
                    roomDatabase.patternDao().InsertPattern(pattern);
                }
                Level level = new Level(unlock_points);
                roomDatabase.levelDao().InsertLevel(level);
//                game game = new game();
//                game.setLevel_no(level_no);
//                game.setUnlock_points(unlock_points);
//                game.setQuestionsArrayList(questionsArrayList);
//                levelArrayList.add(game);
            }
        } catch (
                JSONException e) {
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


//    JSONArray jsonArray = new JSONArray(assets);
//    ArrayList<game> gameArrayList = new ArrayList<>();
//            for (int i = 0; i < jsonArray.length(); i++) {
//        JSONObject gameJsonObject = new JSONObject(jsonArray.get(i).toString());
//        int level_no = gameJsonObject.getInt("level_no");
//        int unlock_points = gameJsonObject.getInt("unlock_points");
//
//        JSONArray questionsJsonArray = gameJsonObject.getJSONArray("questions");
//        ArrayList<questions> questionsArrayList = new ArrayList<>();
//        for (int j = 0; j < questionsJsonArray.length(); j++) {
//        JSONObject questionsJsonObject = new JSONObject(questionsJsonArray.get(i).toString());
//        int id = questionsJsonObject.getInt("id");
//        String title = questionsJsonObject.getString("title");
//        String answer_1 = questionsJsonObject.getString("answer_1");
//        String answer_2 = questionsJsonObject.getString("answer_2");
//        String answer_3 = questionsJsonObject.getString("answer_3");
//        String answer_4 = questionsJsonObject.getString("answer_4");
//        String true_answer = questionsJsonObject.getString("true_answer");
//        int points = questionsJsonObject.getInt("points");
//        int duration = questionsJsonObject.getInt("duration");
//        String hint = questionsJsonObject.getString("hint");
//        JSONObject patternJsonObject = questionsJsonObject.getJSONObject("pattern");
//        int pattern_id = patternJsonObject.getInt("pattern_id");
//        String pattern_name = patternJsonObject.getString("pattern_name");
//
//        questions questions = new questions();
//        pattern pattern = new pattern();
//        pattern.setPattern_id(pattern_id);
//        pattern.setPattern_name(pattern_name);
//
//        questions.setId(id);
//        questions.setTitle(title);
//        questions.setAnswer_1(answer_1);
//        questions.setAnswer_2(answer_2);
//        questions.setAnswer_3(answer_3);
//        questions.setAnswer_4(answer_4);
//        questions.setTrue_answer(true_answer);
//        questions.setPoints(points);
//        questions.setDuration(duration);
//        questions.setHint(hint);
//        questions.setPattern(pattern);
//
//        questionsArrayList.add(questions);
