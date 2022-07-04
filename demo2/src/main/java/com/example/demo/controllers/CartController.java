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
        List<Game> games = (List<Game>) session.getAttribute("cart");
        String list = "";
        for(int i = 0; i < games.size();i++)
        {
            l+= games.get(i).getId();
            if( i + 1 != games.size() ) l+=",";
        }

        HttpHeaders gameHeaders = new HttpHeaders();
        gameHeaders.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        try
        {
            HttpEntity<Boolean> entity = new HttpEntity<>(gameHeaders);
            restTemplate.exchange("http://localhost:8081/addGamesToLibrary?cart={cart}&user={user}",
                HttpMethod.POST, entity, Boolean.class, list, ((User) session.getAttribute("currentUser")).getId() );
        }
        catch (Exception e){}

        session.setAttribute("cart", new ArrayList<>() );
        model.addAttribute("thisUserCart", session.getAttribute("cart") );

        return "redirect:/";
    }
}
