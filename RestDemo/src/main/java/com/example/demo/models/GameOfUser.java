package com.example.demo.models;

import org.springframework.data.annotation.Id;

import java.util.List;

public class GameOfUser
{
    @Id
    private int id;
    private int idOfUser;
    private int idOfGame;

    public GameOfUser() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdOfGame() {
        return idOfGame;
    }

    public void setIdOfGame(int idOfGame) {
        this.idOfGame = idOfGame;
    }

    public int getIdOfUser() {
        return idOfUser;
    }

    public void setIdOfUser(int idOfUser) {
        this.idOfUser = idOfUser;
    }
}
