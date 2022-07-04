package com.example.demo.controllers;

import com.example.demo.models.Game;
import com.example.demo.models.User;
import com.example.demo.services.CartService;
import com.example.demo.services.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController
{
    @Autowired
    IndexService indexService;

    @Autowired
    CartService cartService;

    // Страница корзины
    @GetMapping("/cart")
    public String loadCartPage(Model model, HttpSession session)
    {
        if( session.getAttribute("currentUser") == null )
            return "redirect:/";

        model.addAttribute("thisUserCart", (List<Game>) session.getAttribute("cart") );
        return "cartPage";
    }

    @PostMapping("/cart")
    public String afterBying(HttpSession session, Model model)
    {
        List<Integer> gameList = new ArrayList<>();
        for (Game gm: (List<Game>) session.getAttribute("cart"))
            gameList.add(gm.getId());

        System.out.println( gameList );
        HttpHeaders gameHeaders = new HttpHeaders();
        gameHeaders.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        try
        {
            HttpEntity<Boolean> entity = new HttpEntity<>(gameHeaders);
            restTemplate.exchange(String.format("http://localhost:8081/addGamesToLibrary?cart=%s&user=%d",
                gameList.toString(), ((User)session.getAttribute("currentUser")).getId() ),
                    HttpMethod.POST, entity, Boolean.class);
        }
        catch (Exception e){}

        session.setAttribute("cart", new ArrayList<>() );
        model.addAttribute("thisUserCart", session.getAttribute("cart") );

        return "redirect:/";
    }
}
