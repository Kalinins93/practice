package com.example.demo.controllers;

import com.example.demo.database.BannedRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.database.GamesRepo;
import com.example.demo.database.UsersRepo;
import org.springframework.ui.Model;
import com.example.demo.models.*;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@RestController
public class IndexController
{
    @Autowired
    UsersRepo usersRepo;

    @Autowired
    GamesRepo gamesRepo;

    @GetMapping("/getCurrentUser")
    public Users getCurrentUser(HttpSession session)
    {
        return (Users) session.getAttribute("currentUser");
    }

    @GetMapping("/getAllUser")
    public List<Users> getAllUser()
    {
        return (List<Users>) usersRepo.findAll();
    }

    @GetMapping("/getAllGames")
    public List<Games> getAllGames()
    {
        return (List<Games>) gamesRepo.findAll();
    }
/*
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
*/
}
