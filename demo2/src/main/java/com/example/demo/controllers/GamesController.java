package com.example.demo.controllers;

import com.example.demo.models.Game;
import com.example.demo.models.User;
import com.example.demo.services.AdminService;
import com.example.demo.services.CartService;
import com.example.demo.services.GameService;
import com.example.demo.services.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.nio.file.FileStore;
import java.util.ArrayList;
import java.util.List;

@Controller
public class GamesController
{
    @Autowired
    IndexService indexService;

    @Autowired
    CartService cartService;

    @Autowired
    GameService gameService;

    @Autowired
    AdminService adminService;

    // Страница для игры
    @GetMapping("/games")
    public String loadGamePage(Model model, @RequestParam Integer gameId, HttpSession session)
    {
        User usr = (User) session.getAttribute("currentUser");
        if ( usr != null && adminService.isBanned( usr.getId() )  )
            return "redirect:/ban";

        Game game = gameService.getGameById(gameId);

        model.addAttribute("thisGame", game);
        model.addAttribute("thisGameScreenshots", gameService.getScreenshotsNames(game.getId()));
        return "gamePage";
    }

    // Добавление в корзину
    @PostMapping("/games")
    public String addToCart(@RequestParam Integer gameId, HttpSession session)
    {
        Game game = gameService.getGameById(gameId);

        List<Game> gameList = (List<Game>) session.getAttribute("cart");

        if( gameList == null ) gameList = new ArrayList<>();
        if( !gameList.contains( game ) ) gameList.add( game );

        session.setAttribute( "cart", gameList );

        return "redirect:/";
    }

    // Добавление игры
    @GetMapping("/addGame")
    public String loadAddGamePage(HttpSession session)
    {
        User usr = (User) session.getAttribute("currentUser");

        if( usr == null || ! adminService.isAdmin( usr.getId() ) )
            return "redirect:/";

        return "addGamePage";
    }

    @PostMapping("/addGame")
    public String afterAddGamePage(@RequestParam String title, @RequestParam String description, @RequestParam Integer price,
                                   @RequestParam MultipartFile poster, @RequestParam List<MultipartFile> screenshots )
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate restTemplate = new RestTemplate();
        try
        {
            HttpEntity<Boolean> entity = new HttpEntity<>(headers);
            restTemplate.exchange(
                String.format("http://localhost:8081/addGame?title=%s&description=%s&price=%s&poset=%s&screenshots=%s",
                    title, description, price, poster, screenshots),
                        HttpMethod.POST, entity, Boolean.class );
        }
        catch (Exception e){}

        return "redirect:/";
    }

}
