package com.example.demo.services;

import com.example.demo.models.Game;
import com.example.demo.models.User;
import com.example.demo.requestentitys.GameRequest;
import com.example.demo.requestentitys.UserRequest;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userService")
public class UserService
{
    public User getUserByItsEmail(String email)
    {
        User usr = new User();

        HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate restTemplate = new RestTemplate();
        try
        {
            HttpEntity<UserRequest> entity = new HttpEntity<>(headers);
            ResponseEntity<User> responseUser = restTemplate.exchange(
            "http://localhost:8081/getUserByEmail?email="+email,
                    HttpMethod.POST, entity, User.class);
            usr = responseUser.getBody();
        }
        catch (Exception e){
            throw e;
        }
        return usr;
    }

    public User getUserById(int id)
    {
        User usr = new User();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        try
        {
            HttpEntity<UserRequest> entity = new HttpEntity<>(headers);
            ResponseEntity<User> responseUser = restTemplate.exchange(
                    "http://localhost:8081/getUser?id="+id,
                    HttpMethod.POST, entity, User.class);
            usr = responseUser.getBody();
        }
        catch (Exception e){}
        return usr;
    }

    public List<Game> getUserLibrary(int id)
    {
        List<Game> lib = new ArrayList<>();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        try
        {
            HttpEntity<GameRequest> entity = new HttpEntity<>(headers);
            ResponseEntity<? extends List> responseUser = restTemplate.exchange(
                    "http://localhost:8081/getUserLibrary?id="+id,
                    HttpMethod.POST, entity, lib.getClass());
            lib = responseUser.getBody();
        }
        catch (Exception e){}
        return lib;
    }

    public boolean isUserHaveGame(int idofuser, int idofgame)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        try
        {
            HttpEntity<Boolean> entity = new HttpEntity<>(headers);
            ResponseEntity<Boolean> responseUser = restTemplate.exchange(
                String.format("http://localhost:8081/haveGame?idofuser=%s&idofgame=%s",idofuser, idofgame),
                    HttpMethod.POST, entity, Boolean.class);
            return responseUser.getBody();
        }
        catch (Exception e){}

        return false;
    }
}
