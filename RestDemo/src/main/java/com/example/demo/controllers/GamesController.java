package com.example.demo.controllers;

import com.example.demo.database.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpSession;
import com.example.demo.models.Game;
import java.sql.SQLException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

@RestController
public class GamesController
{
    @Autowired
    DataSource dataSource;

    @Autowired
    GamesRepo gamesRepo;

    @Autowired
    ScreenshotsRepo screenshotsRepo;

    @PostMapping("/getGame")
    public Game getGame(@RequestParam int gameId)
    {
        return gamesRepo.getById(gameId);
    }

    @PostMapping("/getGameScreenshots")
    public List<String> getGameScreenshots(@RequestParam int gameId)
    {
        return screenshotsRepo.getNamesByGameId(gameId);
    }

    @PostMapping("/addGameToCart")
    public boolean addGameToCart(HttpSession session, @RequestParam int gameId)
    {
        List<Game> gs = (List<Game>) session.getAttribute("cart");
        gs.add( gamesRepo.getById(gameId) );
        session.setAttribute( "cart", gs );
        return true;
    }

    @PostMapping("/addGame")
    public boolean afterAddGamePage(@RequestParam String title, @RequestParam String description,
                                   @RequestParam int price, @RequestParam MultipartFile poster,
                                   @RequestParam List<MultipartFile> screenshots ) throws IOException, SQLException
    {
        Connection con = dataSource.getConnection();
        Statement stm = con.createStatement();

        //Добавление игры в бд
        stm.executeUpdate(String.format("insert into games (title, description, price) values ('%s', '%s', %d)", title, description, price));

        int thisGameId = gamesRepo.getMaxId();
        String posterName = gamesRepo.getImageNameById( thisGameId );
        //RestDemoApplication.CopyToFolderImage(poster, posterName);

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
            //RestDemoApplication.CopyToFolderImage(file, newScreenshotName);
        }

        con.close();
        return true;
    }

}
