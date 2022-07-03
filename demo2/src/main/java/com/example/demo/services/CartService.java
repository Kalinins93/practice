package com.example.demo.services;

import com.example.demo.models.Game;
import com.example.demo.requestentitys.GameRequest;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;

@Service("cartService")
public class CartService
{
    public List<Game> getCart()
    {
        List<Game> cart = new ArrayList<>();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        try
        {
            HttpEntity<GameRequest> entity = new HttpEntity<>(headers);
            ResponseEntity<? extends List> responseUser = restTemplate.exchange(
                    "http://localhost:8081/getCart",
                    HttpMethod.GET, entity, cart.getClass());
            cart = responseUser.getBody();
        }
        catch (Exception e){}

        return cart;
    }

    public boolean emptyCart()
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        try
        {
            HttpEntity<Boolean> entity = new HttpEntity<>(headers);
            restTemplate.exchange("http://localhost:8081/emptyCart",
                    HttpMethod.GET, entity, Boolean.class);
        }
        catch (Exception e){}
        return true;
    }
}
