package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import com.example.demo.database.GameOfUserRepo;
import javax.servlet.http.HttpSession;
import com.example.demo.models.Games;
import com.example.demo.models.Users;
import org.springframework.ui.Model;
import java.sql.SQLException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.util.ArrayList;
import java.sql.Statement;
import java.util.List;

@Controller
public class CartController
{
    @Autowired
    DataSource dataSource;

    @Autowired
    GameOfUserRepo gameOfUserRepo;

    // Страница корзины
    @GetMapping("/cart")
    public String loadCartPage(HttpSession session, Model model)
    {
        if(session.getAttribute("currentUser") == null)
            return "redirect:/";

        model.addAttribute("thisUserCart", session.getAttribute("cart") );
        return "cartPage";
    }

    @PostMapping("/cart")
    public String afterBying(HttpSession session, Model model) throws SQLException
    {
        List<Games> gamesToBuy = (List<Games>) session.getAttribute("cart");
        int curretnUserId = ( (Users) session.getAttribute("currentUser") ).getId();
        Connection con = dataSource.getConnection();
        Statement stm = con.createStatement();

        for (Games game: gamesToBuy )
        {
            if( gameOfUserRepo.contains( curretnUserId ,game.getId()) ) continue;
            stm.executeUpdate("insert into Libraries (idofuser, idofgame) values ("+ curretnUserId + ", " + game.getId() + " )");
        }
        con.close();

        session.setAttribute("cart", new ArrayList<Games>() );
        model.addAttribute("thisUserCart", session.getAttribute("cart") );

        return "redirect:/";
    }
}
