package com.example.demo.controllers;

import com.example.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.demo.database.GameOfUserRepo;
import javax.servlet.http.HttpSession;
import com.example.demo.models.Game;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.sql.SQLException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.util.ArrayList;
import java.sql.Statement;
import java.util.List;

@RestController
public class CartController
{
    @Autowired
    DataSource dataSource;

    @Autowired
    GameOfUserRepo gameOfUserRepo;

    @PostMapping("/addGamesToLibrary")
    public boolean addGamesToLibrary(List<Game> cart, int currentUser) throws SQLException
    {
        Connection con = dataSource.getConnection();
        Statement stm = con.createStatement();

        for (Game game: cart )
        {
            if( gameOfUserRepo.contains( currentUser ,game.getId()) ) continue;
            stm.executeUpdate("insert into libraries (idofuser, idofgame) values ("+ currentUser + ", " + game.getId() + " )");
        }
        con.close();
        return true;
    }
}