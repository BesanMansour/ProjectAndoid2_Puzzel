package com.example.finalproject.Json;

import java.util.ArrayList;

public class GameResponse {
    ArrayList<game> gameArrayList;

    public GameResponse(ArrayList<game> gameArrayList) {
        this.gameArrayList = gameArrayList;
    }

    public GameResponse() {
    }

    public ArrayList<game> getGameArrayList() {
        return gameArrayList;
    }

    public void setGameArrayList(ArrayList<game> gameArrayList) {
        this.gameArrayList = gameArrayList;
    }
}

