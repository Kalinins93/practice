package com.example.demo.controllers;

import com.example.demo.database.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Controller;
import com.example.demo.DemoApplication;
import javax.servlet.http.HttpSession;
import com.example.demo.models.Games;
import com.example.demo.models.Users;
import org.springframework.ui.Model;
import java.sql.SQLException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.sql.Statement;
import java.util.List;

@Controller
public class GamesController
{
    @Autowired
    DataSource dataSource;

    @Autowired
    GamesRepo gamesRepo;

    @Autowired
    BannedRepo bannedRepo;

    @Autowired
    GameOfUserRepo gameOfUserRepo;

    @Autowired
    ScreenshotsRepo screenshotsRepo;

    @Autowired
    RolesRepo rolesRepo;

    // Страница для игры
    @GetMapping("/games")
    public String loadGamePage(Model model, HttpSession session, @RequestParam Integer gameId)
    {
        Users usr = (Users) session.getAttribute("currentUser");
        if ( usr != null && bannedRepo.isBanned( usr.getId() )  )
            return "redirect:/ban";
        Games game = gamesRepo.getById(gameId);

        if( usr != null )
        System.out.println( gameOfUserRepo.contains( usr.getId() ,game.getId()) );

        model.addAttribute("thisGame", game);
        model.addAttribute("thisGameScreenshots", screenshotsRepo.getNamesByGameId(game.getId()));
        return "gamePage";
    }

    // Добавление в корзину
    @PostMapping("/games")
    public String addToCart(HttpSession session, @RequestParam Integer gameId)
    {
        if( session.getAttribute("cart") == null )
            session.setAttribute("cart", new ArrayList<Games>() );

        List<Games> gs = (List<Games>) session.getAttribute("cart");
        gs.add( gamesRepo.getById(gameId) );
        session.setAttribute( "cart", gs );

        return "redirect:/";
    }

    // Добавление игры
    @GetMapping("/addGame")
    public String loadAddGamePage(HttpSession session)
    {
        Users usr = ((Users) session.getAttribute("currentUser"));

        if(  session.getAttribute("currentUser") == null || ! rolesRepo.getAllAdmins().contains( usr.getId() ) )
            return "redirect:/";

        return "addGamePage";
    }

    @PostMapping("/addGame")
    public String afterAddGamePage(@RequestParam String title, @RequestParam String description, @RequestParam Integer price,
                                   @RequestParam MultipartFile poster, @RequestParam List<MultipartFile> screenshots ) throws IOException, SQLException
    {
        Connection con = dataSource.getConnection();
        Statement stm = con.createStatement();

        //Добавление игры в бд
        stm.executeUpdate(String.format("insert into Games (title, description, price) values ('%s', '%s', %d)", title, description, price));

        int thisGameId = gamesRepo.getMaxId();
        String posterName = gamesRepo.getImageNameById( thisGameId );
        DemoApplication.CopyToFolderImage(poster, posterName);

        int thisScreenshotId = 0;
        String newScreenshotName = "";

        //Добавление скриншотов в бд
        for(MultipartFile file : screenshots)
        {
            stm.executeUpdate(String.format("insert into Screenshots (idofgame, screenshotname) values (%d, '%s' )",
                    thisGameId, "placeholder"));
            thisScreenshotId = screenshotsRepo.getMaxId();
            newScreenshotName = thisGameId + "_"+ title + "_" + thisScreenshotId + ".jpg";
            stm.executeUpdate(String.format("update Screenshots set screenshotname = '%s' where id = %d",
                    newScreenshotName, thisScreenshotId));
            DemoApplication.CopyToFolderImage(file, newScreenshotName);
        }

        con.close();
        return "redirect:/";
    }

}
