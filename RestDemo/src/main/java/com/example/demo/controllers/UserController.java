package com.example.demo.controllers;

import com.example.demo.database.GameOfUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.database.GamesRepo;
import com.example.demo.database.UsersRepo;
import com.example.demo.models.Libraries;
import javax.servlet.http.HttpSession;
import com.example.demo.models.User;

@RestController
public class UserController
{
    @Autowired
    GamesRepo gamesRepo;

    @Autowired
    UsersRepo usersRepo;

    @Autowired
    GameOfUserRepo gameOfUserRepo;

    @PostMapping("/getUser")
    public User getUser(@RequestParam(value = "id", required = true) int id)
    {
        return usersRepo.findById(id).get();
    }

    @PostMapping("/getUserByEmail")
    public User getUser(@RequestParam(value = "email", required = true) String email)
    {
        return usersRepo.getByEmail(email);
    }

    @PostMapping("/getUserLibrary")
    public Libraries getUserLibrary(@RequestParam int id)
    {
        Libraries libra = new Libraries();
        libra.setGames ( gamesRepo.getAllGamesOfUser( id ) );
        return libra;
    }

    @PostMapping("/updateUserInfo")
    public boolean updateUserInfo(@RequestParam int pageId, @RequestParam String name,
                                  @RequestParam String email, @RequestParam String hashcode,
                                  //@RequestParam MultipartFile icon,
                                  @RequestParam int currentId)
    {
        User user = usersRepo.findById(currentId).get();

        if( usersRepo.findById(pageId).get().getId() != user.getId() )
            return false;

        user.setName(name);
        user.setEmail(email);
        user.setHashcode(hashcode);
/*
        if( icon.isEmpty() )
            usr.setIconname( usersRepo.findById(id).get().getIconname() );
        else
        {
            //RestDemoApplication.CopyToFolderImage(icon, id + "_icon.jpg");
            usr.setIconname(id + "_icon.jpg");
        }
 */
        usersRepo.save( user );
        return true;
    }

    @PostMapping("/haveGame")
    public boolean hasGame(@RequestParam int idofuser,@RequestParam int idofgame)
    {
        return gameOfUserRepo.contains(idofuser, idofgame);
    }
}
