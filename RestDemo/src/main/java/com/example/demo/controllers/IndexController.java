package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.database.GamesRepo;
import com.example.demo.database.UsersRepo;
import com.example.demo.models.*;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class IndexController
{
    @Autowired
    UsersRepo usersRepo;

    @Autowired
    GamesRepo gamesRepo;

    @PostMapping("/getAllUser")
    public List<User> getAllUser()
    {
        return (List<User>) usersRepo.findAll();
    }

    @PostMapping("/getAllGames")
    public List<Game> getAllGames()
    {
        return (List<Game>) gamesRepo.findAll();
    }

    @PostMapping("/getAllGamesByKeyword")
    public List<Game> getAllGamesByKeyword(@RequestParam String keyword)
    {
        return gamesRepo.findAllByKeyword(keyword);
    }
}
