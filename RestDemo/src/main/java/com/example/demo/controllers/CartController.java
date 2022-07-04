package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.demo.database.GameOfUserRepo;
import com.example.demo.models.Game;
import org.springframework.web.bind.annotation.RestController;
import java.sql.SQLException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CartController
{
    @Autowired
    DataSource dataSource;

    @Autowired
    GameOfUserRepo gameOfUserRepo;

    @PostMapping("/addGamesToLibrary")
    public boolean addGamesToLibrary(List<Integer> cart, int user)
    {
        try
        {
            Connection con = dataSource.getConnection();
            Statement stm = con.createStatement();

            for (Integer game : cart)
            {
                if (gameOfUserRepo.contains(user, game)) continue;
                stm.executeUpdate("insert into libraries (idofuser, idofgame) values (" + user + ", " + game + " )");
            }

            con.close();
        }
        catch (SQLException e){}

        return true;
    }
}