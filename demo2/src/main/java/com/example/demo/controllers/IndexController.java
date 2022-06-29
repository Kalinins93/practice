package com.example.demo.controllers;

import com.example.demo.database.BannedRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import com.example.demo.database.GamesRepo;
import com.example.demo.database.UsersRepo;
import org.springframework.ui.Model;
import com.example.demo.models.*;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.util.ArrayList;

@Controller
public class IndexController
{
    @Autowired
    DataSource dataSource;

    @Autowired
    UsersRepo usersRepo;

    @Autowired
    BannedRepo bannedRepo;

    @Autowired
    GamesRepo gamesRepo;

    @GetMapping("/")
    public String loadIndexPage(Model model, HttpSession session)
    {
        Users usr = (Users) session.getAttribute("currentUser");
        if ( usr != null && bannedRepo.isBanned( usr.getId() )  )
            return "redirect:/ban";

        if ( session.getAttribute("cart") == null  )
            session.setAttribute("cart", new ArrayList<Games>());

        model.addAttribute("games", gamesRepo.findAll() );
        return "index";
    }

    @GetMapping("/search")
    public String search(Model model, HttpSession session, @RequestParam String keyword)
    {
        Users usr = (Users) session.getAttribute("currentUser");
        if ( usr != null && bannedRepo.isBanned( usr.getId() )  )
            return "redirect:/ban";

        if( keyword.isEmpty() )
            return "redirect:/";

        System.out.println(gamesRepo.findAllByKeyword(keyword));
        model.addAttribute("games", gamesRepo.findAllByKeyword(keyword) );
        return "index";
    }

    @GetMapping("/ban")
    public String ban(HttpSession session)
    {
        if( session.getAttribute("currentUser") == null )
            return "redirect:/";

        return "banPage";
    }


/*
    // Страница с ошибкой
    @GetMapping("/error")
    public String loadErrorPage()
    {
        return "error";
    }
*/
}
