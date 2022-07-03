package com.example.demo.models;

import java.util.List;

public class Libraries
{
    private int idOfUser;
    private List<Game> games;

    public int getIdOfUser() {
        return idOfUser;
    }

    public void setIdOfUser(int idOfUser) {
        this.idOfUser = idOfUser;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public void addGame(Game game)
    {
        games.add(game);
    }
}
