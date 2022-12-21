package com.example.finalproject.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import android.os.Bundle;
import android.widget.Toast;

import com.example.finalproject.Fragment.LevelAdapterFragment;
import com.example.finalproject.Fragment.TrueFalseFragment;
import com.example.finalproject.Json.ParsJson;
import com.example.finalproject.databinding.ActivityLevelBinding;
import com.example.finalproject.Json.game;
import com.example.finalproject.Json.questions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import RoomDatabase.ViewModel;

public class LevelActivity extends AppCompatActivity {
    ActivityLevelBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLevelBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewModel viewModel = new ViewModelProvider(this).get(ViewModel.class);

        String assets = ParsJson.readFromAssets(getApplicationContext(),"json/jsonStr.json");
        ParsJson p = new ParsJson(this);
        p.readJson(assets);
    }

    private void parsJson(String assets) {
        String title = null;
        int points = 0;
        String true_answer = null;
        try {
            JSONArray jsonArray = new JSONArray(assets);
            ArrayList<game> gameArrayList = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject gameJsonObject = new JSONObject(jsonArray.get(i).toString());
                int level_no = gameJsonObject.getInt("level_no");

                JSONArray questionsJsonArray = gameJsonObject.getJSONArray("questions");
                ArrayList<questions> questionsArrayList = new ArrayList<>();
                for (int j = 0; j < questionsJsonArray.length(); j++) {
                    JSONObject questionsJsonObject = new JSONObject(questionsJsonArray.get(i).toString());
                    int id = questionsJsonObject.getInt("id");
                    title = questionsJsonObject.getString("title");
                    String answer_1 = questionsJsonObject.getString("answer_1");
                    String answer_2 = questionsJsonObject.getString("answer_2");
                    String answer_3 = questionsJsonObject.getString("answer_3");
                    String answer_4 = questionsJsonObject.getString("answer_4");
                    true_answer = questionsJsonObject.getString("true_answer");
                    points = questionsJsonObject.getInt("points");
                    int duration = questionsJsonObject.getInt("duration");
                    String hint = questionsJsonObject.getString("hint");
                    JSONObject patternJsonObject = questionsJsonObject.getJSONObject("pattern");
                    int pattern_id = patternJsonObject.getInt("pattern_id");
                    String pattern_name = patternJsonObject.getString("pattern_name");

                    questions questions = new questions();
//                    pattern pattern = new pattern();
//                    pattern.setPattern_id(pattern_id);
//                    pattern.setPattern_name(pattern_name);

                    questions.setId(id);
                    questions.setTitle(title);
                    questions.setAnswer_1(answer_1);
                    questions.setAnswer_2(answer_2);
                    questions.setAnswer_3(answer_3);
                    questions.setAnswer_4(answer_4);
                    questions.setTrue_answer(true_answer);
                    questions.setPoints(points);
                    questions.setDuration(duration);
                    questions.setHint(hint);
//                    questions.setPattern(pattern);

                    questionsArrayList.add(questions);

                }
                game game = new game();
                game.setLevel_no(level_no);
                game.setQuestionsArrayList(questionsArrayList);

                ArrayList<Fragment> fragments = new ArrayList<>();
                fragments.add(TrueFalseFragment.newInstance(title,true_answer));

                Toast.makeText(this, String.valueOf(true_answer), Toast.LENGTH_SHORT).show();

                LevelAdapterFragment carFragAdapter = new LevelAdapterFragment(this, fragments);
                binding.LevelPager.setAdapter(carFragAdapter);

                binding.LevelNumMystery.setText(String.valueOf(points));
                binding.LevelNumPoint.setText(String.valueOf(questionsArrayList.size()));
                binding.LevelTV.setText("المجموعة "+ level_no);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String readFromAssets(String fileName) {
        String string = "";
        try {
            InputStream inputStream = getAssets().open(fileName);
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