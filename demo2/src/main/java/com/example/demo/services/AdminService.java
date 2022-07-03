package com.example.demo.services;

import com.example.demo.models.User;
import com.example.demo.requestentitys.UserRequest;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;

@Service("adminService")
public class AdminService
{
    public List<User> getAllUsers()
    {
        List<User> users = new ArrayList<>();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        try
        {
            HttpEntity<UserRequest> entity = new HttpEntity<>(headers);
            ResponseEntity<? extends List> responseUser = restTemplate.exchange(
            "http://localhost:8081/getAllUsers",
                    HttpMethod.POST, entity, users.getClass());
            users = responseUser.getBody();
        }
        catch (Exception e){}
        return users;
    }

    public boolean isBanned(Integer id)
    {
        List<Integer> list = new ArrayList<>();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        try
        {
            HttpEntity<Integer> entity = new HttpEntity<>(headers);
            ResponseEntity<? extends List> responseBanned = restTemplate.exchange("http://localhost:8081/getAllBanned",
                    HttpMethod.POST, entity, list.getClass());
            return responseBanned.getBody().contains( id );
        }
        catch (Exception e){}

        return false;
    }

    public boolean isAdmin(int id)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        try
        {
            HttpEntity<Boolean> entity = new HttpEntity<>(headers);
            ResponseEntity<Boolean> responseUser = restTemplate.exchange(
                    "http://localhost:8081/isAdmin?id="+id,
                    HttpMethod.POST, entity, Boolean.class);
            return responseUser.getBody();
        }
        catch (Exception e){}

        return false;
    }

    public boolean unbanUser(int id)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        try
        {
            HttpEntity<Boolean> entity = new HttpEntity<>(headers);
            ResponseEntity<Boolean> responseUser = restTemplate.exchange(
                    "http://localhost:8081/unbanUser?id="+id,
                    HttpMethod.POST, entity, Boolean.class);
            return responseUser.getBody();
        }
        catch (Exception e){}

        return false;
    }

    public boolean banUser(int id)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        try
        {
            HttpEntity<Boolean> entity = new HttpEntity<>(headers);
            ResponseEntity<Boolean> responseUser = restTemplate.exchange(
                    "http://localhost:8081/banUser?id="+id,
                    HttpMethod.POST, entity, Boolean.class);
            return responseUser.getBody();
        }
        catch (Exception e){}

        return false;
    }

    public boolean revokeAdmin(int id)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        try
        {
            HttpEntity<Boolean> entity = new HttpEntity<>(headers);
            ResponseEntity<Boolean> responseUser = restTemplate.exchange(
                    "http://localhost:8081/revokeAdmin?id="+id,
                    HttpMethod.POST, entity, Boolean.class);
            return responseUser.getBody();
        }
        catch (Exception e){}

        return false;
    }

    public boolean grantAdmin(int id)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        try
        {
            HttpEntity<Boolean> entity = new HttpEntity<>(headers);
            ResponseEntity<Boolean> responseUser = restTemplate.exchange(
                    "http://localhost:8081/grantAdmin?id="+id,
                    HttpMethod.POST, entity, Boolean.class);
            return responseUser.getBody();
        }
        catch (Exception e){}

        return false;
    }
}
