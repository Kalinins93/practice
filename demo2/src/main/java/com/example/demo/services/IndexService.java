package com.example.demo.services;

import com.example.demo.models.User;
import com.example.demo.requestentitys.UserRequest;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("indexService")
public class IndexService
{
    public User getCurrentUser()
    {
        User usr = new User();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        try
        {
            HttpEntity<UserRequest> entity = new HttpEntity<>(headers);
            ResponseEntity<User> responseUser = restTemplate.exchange(
                    "http://localhost:8081/getCurrentUser", HttpMethod.POST, entity, User.class);
            usr = responseUser.getBody();
        }
        catch (Exception e){
            throw e;
        }
        return usr;
    }

    public boolean setCurrentUser(User user)
    {
        if(user == null) return false;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        try
        {
            HttpEntity<UserRequest> entity = new HttpEntity<>(headers);
            restTemplate.exchange("http://localhost:8081/getCurrentUser", HttpMethod.POST, entity, User.class, user);
        }
        catch (Exception e){}
        return true;
    }

    public void logout()
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        try
        {
            HttpEntity<Boolean> entity = new HttpEntity<>(headers);
            restTemplate.exchange("http://localhost:8081/logout",HttpMethod.POST, entity, Boolean.class );
        }
        catch (Exception e){}
    }
}
