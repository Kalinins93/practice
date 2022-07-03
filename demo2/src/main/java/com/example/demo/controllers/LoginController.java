package com.example.demo.controllers;

import com.example.demo.models.User;
import com.example.demo.services.IndexService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import javax.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;
import java.sql.SQLException;

@Controller
public class LoginController
{
    @Autowired
    IndexService indexService;

    @Autowired
    UserService userService;

    // Страница со входом
    @GetMapping("/login")
    public String loadLoginPage(Model model)
    {
        return "login";
    }

    @PostMapping("/login")
    public String loadAfterLogin(HttpSession session, @RequestParam String email,
                                 @RequestParam String hashcode, Model model)
    {
        User usr = userService.getUserByItsEmail(email);
        System.out.println(usr.toString());

        if( usr.getHashcode() == null )
            return "redirect:/login";

        if( !usr.getHashcode().equals( hashcode ) )
            return "redirect:/login";

        session.setAttribute("currentUser", usr);
        model.addAttribute("session", session );

        return "redirect:/";
    }

    //Выход из сети
    @GetMapping("/logout")
    public String logout(HttpSession session)
    {
        session.setAttribute("currentUser", null);
        return "redirect:/";
    }

    @GetMapping("/reg")
    public String loadRegisatrationPage()
    {
        return "RegistrationPage";
    }

    @PostMapping("/reg")
    public String afterRegisatration(@RequestParam String name, @RequestParam String email,
                                     @RequestParam String hashcode )
    {
        User usr = userService.getUserByItsEmail(email);

        if( usr != null )
            return "redirect:/reg";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate restTemplateReg = new RestTemplate();
        try
        {
            HttpEntity<Boolean> entity = new HttpEntity<>(headers);
            restTemplateReg.exchange(String.format("http://localhost:8081/registerUser?name=%s&email=%s&hashcode=%s",name, email, hashcode),
                    HttpMethod.POST, entity, Boolean.class);
        }
        catch (Exception e){}

        return "redirect:/login";
    }

}
