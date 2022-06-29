package com.example.demo.models;

import java.util.List;

public class Libraries
{
    private int idOfUser;
    private List<Games> games;

    public int getIdOfUser() {
        return idOfUser;
    }

    public void setIdOfUser(int idOfUser) {
        this.idOfUser = idOfUser;
    }

    public List<Games> getGames() {
        return games;
    }

    public void setGames(List<Games> games) {
        this.games = games;
    }

    public void addGame(Games game)
    {
        games.add(game);
    }
}
