package com.example.demo.controllers;

import com.example.demo.models.User;
import com.example.demo.services.IndexService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpSession;

@Controller
public class UserController
{
    @Autowired
    IndexService indexService;

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public String loadUserPage(Model model, @RequestParam int id)
    {
        model.addAttribute("thisUser", userService.getUserById(id));
        model.addAttribute("thisLibraryOfThisUser", userService.getUserLibrary(id));

        return "userPage";
    }

    @GetMapping("/editUser")
    public String loadEdituserPage(@RequestParam int id, HttpSession session, Model model)
    {
        User currentUser = (User) session.getAttribute("currentUser");
        User pageUser = userService.getUserById(id);

        if( currentUser == null || currentUser.getId() != id)
            return "redirect:/";

        model.addAttribute("oldUserdata", pageUser);

        return "editUserPage";
    }

    @PostMapping("/editUser")
    public String changeUserData(@RequestParam int id, @RequestParam String name,
                                 @RequestParam String email,@RequestParam String hashcode,
                                 HttpSession session)
    {
        User user = (User) session.getAttribute("currentUser");

        if( userService.getUserByItsEmail(email) != null &&
            userService.getUserByItsEmail(email).getId() != user.getId())
                return "redirect:/editUser?id="+id;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        try
        {
            HttpEntity<Boolean> entity = new HttpEntity<>(headers);
            restTemplate.exchange(String.format(
                "http://localhost:8081/updateUserInfo?pageId=%d&name=%s&email=%s&hashcode=%s&currentId=%d", id, name, email, hashcode, user.getId()),
                    HttpMethod.POST, entity, Boolean.class);
        }
        catch (Exception e){}

        return "redirect:/users?id="+id;
    }
}
