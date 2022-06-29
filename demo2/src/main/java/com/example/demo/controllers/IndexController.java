package com.example.demo.controllers;

import com.example.demo.requestentitys.UserRequest;
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
    @GetMapping("/")
    public String loadIndexPage(Model model, HttpSession session)
    {
        Users usr = null;
        List<Games> games = new ArrayList<>();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate restTemplate = new RestTemplate();
        try
        {
            HttpEntity<UserRequest> entity = new HttpEntity<>(headers);
            ResponseEntity<Users> responseUser = restTemplate.exchange("http://localhost:8081/getCurrentUser", HttpMethod.GET, entity, Users.class);
            usr = responseUser.getBody();

            ResponseEntity<? extends List> responseGames = restTemplate.exchange("http://localhost:8081/getAllGames", HttpMethod.GET, entity, games.getClass() );
            games = responseGames.getBody();
        }
        catch (Exception e){}

        if ( session.getAttribute("cart") == null  )
            session.setAttribute("cart", new ArrayList<Games>());

        model.addAttribute("games", games );
        return "index";
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


/*
    // Страница с ошибкой
    @GetMapping("/error")
    public String loadErrorPage()
    {
        return "error";
    }
*/
}
