package com.example.demo.controllers;

import com.example.demo.models.User;
import com.example.demo.services.AdminService;
import com.example.demo.services.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;

@Controller
public class AdminController
{
    @Autowired
    IndexService indexService;

    @Autowired
    AdminService adminService;

    @GetMapping("/admin")
    public String loadAdminPage(HttpSession session)
    {
        User usr = (User) session.getAttribute("currentUser");
        if( usr == null || ! adminService.isAdmin( usr.getId() ) )
            return "redirect:/";

        if ( adminService.isBanned( usr.getId() ) )
            return "redirect:/ban";

        return "adminPage";
    }

    @GetMapping("/allUsers")
    public String loadAllUsersPage(Model model, HttpSession session)
    {
        User usr = (User) session.getAttribute("currentUser");
        if( usr == null || ! adminService.isAdmin( usr.getId() ) )
            return "redirect:/";

        if ( adminService.isBanned( usr.getId() ) )
            return "redirect:/ban";

        model.addAttribute("allUsers", adminService.getAllUsers());

        return "allUsersPage";
    }

    @PostMapping("/unbanUser")
    public String unbanUser(@RequestParam int id)
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
        }
        catch (Exception e){}

        return "redirect:/allUsers";
    }

    @PostMapping("/banUser")
    public String banUser(@RequestParam int id)
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
        }
        catch (Exception e){}

        return "redirect:/allUsers";
    }

    @PostMapping("/revokeAdmin")
    public String revokeAdmin(@RequestParam int id)
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
        }
        catch (Exception e){}

        return "redirect:/allUsers";
    }

    @PostMapping("/grantAdmin")
    public String grantAdmin(@RequestParam int id)
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
        }
        catch (Exception e){}

        return "redirect:/allUsers";
    }

}
