package com.example.demo.controllers;

import com.example.demo.services.AdminService;
import com.example.demo.services.CartService;
import com.example.demo.services.GameService;
import com.example.demo.services.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.example.demo.models.*;
import org.springframework.web.client.RestTemplate;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController
{
    @Autowired
    AdminService adminService;

    @Autowired
    GameService gameService;

    @Autowired
    CartService cartService;

    @GetMapping("/")
    public String loadIndexPage(Model model, HttpSession session)
    {
        User usr = (User) session.getAttribute("currentUser");

        if ( usr != null && adminService.isBanned( usr.getId() )  )
            return "redirect:/ban";

        if ( usr!= null && cartService.getCart() == null  )
            cartService.emptyCart();

        if( usr != null ) System.out.println(usr.toString());
        model.addAttribute("games", gameService.getAllGames() );
        return "index";
    }

    @GetMapping("/search")
    public String search(HttpSession session, Model model, @RequestParam String keyword)
    {
        User usr = (User) session.getAttribute("currentUser");
        if ( usr != null && adminService.isBanned( usr.getId() )  )
            return "redirect:/ban";

        if( keyword.isEmpty() )
            return "redirect:/";

        List<Game> searchGames = new ArrayList<>();

        HttpHeaders gameHeaders = new HttpHeaders();
        gameHeaders.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        try
        {
            HttpEntity<Game> entity = new HttpEntity<>(gameHeaders);
            ResponseEntity<? extends List> responseGames = restTemplate.exchange(
            "http://localhost:8081/getAllGamesByKeyword?keyword=" + keyword,
                    HttpMethod.POST, entity, searchGames.getClass() );
            searchGames = responseGames.getBody();
        }
        catch (Exception e){}

        model.addAttribute("games", searchGames );
        return "index";
    }

    @GetMapping("/ban")
    public String ban(HttpSession session)
    {
        User usr = (User) session.getAttribute("currentUser");
        if( usr == null )
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
