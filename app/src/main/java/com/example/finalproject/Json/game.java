package com.example.finalproject.Json;

import java.util.ArrayList;

public class game {
    int level_no;
    int unlock_points;
    ArrayList<questions> questionsArrayList;

    public game(int level_no, int unlock_points, ArrayList<questions> questionsArrayList) {
        this.level_no = level_no;
        this.unlock_points = unlock_points;
        this.questionsArrayList = questionsArrayList;
    }

    public game() {
    }

    public int getLevel_no() {
        return level_no;
    }

    public void setLevel_no(int level_no) {
        this.level_no = level_no;
    }

    public int getUnlock_points() {
        return unlock_points;
    }

    public void setUnlock_points(int unlock_points) {
        this.unlock_points = unlock_points;
    }

    public ArrayList<questions> getQuestionsArrayList() {
        return questionsArrayList;
    }

    public void setQuestionsArrayList(ArrayList<questions> questionsArrayList) {
        this.questionsArrayList = questionsArrayList;
    }
}
