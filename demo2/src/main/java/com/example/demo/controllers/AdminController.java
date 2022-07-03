package com.example.demo.controllers;

import com.example.demo.models.User;
import com.example.demo.services.AdminService;
import com.example.demo.services.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class AdminController
{
    @Autowired
    IndexService indexService;

    @Autowired
    AdminService adminService;

    @GetMapping("/admin")
    public String loadAdminPage()
    {
        User usr = indexService.getCurrentUser();
        if( usr == null || ! adminService.isAdmin( usr.getId() ) )
            return "redirect:/";

        if ( adminService.isBanned( usr.getId() ) )
            return "redirect:/ban";

        return "adminPage";
    }

    @GetMapping("/allUsers")
    public String loadAllUsersPage(Model model)
    {
        User usr = indexService.getCurrentUser();
        if( usr == null || ! adminService.isAdmin( usr.getId() ) )
            return "redirect:/";

        if ( adminService.isBanned( usr.getId() ) )
            return "redirect:/ban";

        model.addAttribute("allUsers", adminService.getAllUsers());

        return "allUsersPage";
    }


}
