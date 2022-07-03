package com.example.demo.controllers;

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

@Controller
public class CartController
{
    @Autowired
    IndexService indexService;

    @Autowired
    CartService cartService;

    // Страница корзины
    @GetMapping("/cart")
    public String loadCartPage(Model model)
    {
        if( indexService.getCurrentUser() == null )
            return "redirect:/";

        model.addAttribute("thisUserCart", cartService.getCart() );
        return "cartPage";
    }

    @PostMapping("/cart")
    public String afterBying(HttpSession session, Model model)
    {
        HttpHeaders gameHeaders = new HttpHeaders();
        gameHeaders.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        try
        {
            HttpEntity<Boolean> entity = new HttpEntity<>(gameHeaders);
            restTemplate.exchange("http://localhost:8081/addGamesToLibrary",
                    HttpMethod.GET, entity, Boolean.class );
        }
        catch (Exception e){}

        //session.setAttribute("cart", new ArrayList<Game>() );
        model.addAttribute("thisUserCart", session.getAttribute("cart") );

        return "redirect:/";
    }
}
