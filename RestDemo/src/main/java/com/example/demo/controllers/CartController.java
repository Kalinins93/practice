package com.example.demo.controllers;

import com.example.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.demo.database.GameOfUserRepo;
import javax.servlet.http.HttpSession;
import com.example.demo.models.Game;
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

    @PostMapping("/getCart")
    public List<Game> getCart(HttpSession session)
    {
        return (List<Game>) session.getAttribute("cart");
    }

    @PostMapping("/addGamesToLibrary")
    public boolean addGamesToLibrary(HttpSession session) throws SQLException
    {
        List<Game> gamesToBuy = (List<Game>) session.getAttribute("cart");
        int curretnUserId = ( (User) session.getAttribute("currentUser") ).getId();
        Connection con = dataSource.getConnection();
        Statement stm = con.createStatement();

        for (Game game: gamesToBuy )
        {
            if( gameOfUserRepo.contains( curretnUserId ,game.getId()) ) continue;
            stm.executeUpdate("insert into libraries (idofuser, idofgame) values ("+ curretnUserId + ", " + game.getId() + " )");
        }
        con.close();

        session.setAttribute("cart", new ArrayList<Game>() );
        return true;
    }

    @PostMapping("/emptyCart")
    public boolean emptyCart(HttpSession session)
    {
        session.setAttribute("cart", new ArrayList<>() );
        return true;
    }
}