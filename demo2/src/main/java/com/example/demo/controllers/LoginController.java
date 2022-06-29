package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import com.example.demo.database.UsersRepo;
import javax.servlet.http.HttpSession;
import com.example.demo.models.Users;
import org.springframework.ui.Model;
import java.sql.SQLException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;

@Controller
public class LoginController
{
    @Autowired
    DataSource dataSource;

    @Autowired
    UsersRepo usersRepo;

    // Страница со входом
    @GetMapping("/login")
    public String loadLoginPage(Model model)
    {
        Users usr = new Users();
        model.addAttribute("users", usr);
        return "login";
    }

    @PostMapping("/login")
    public String loadAfterLogin(HttpSession session, @RequestParam String email, @RequestParam String hashcode, Model model)
    {
        if( usersRepo.getByEmail( email ) == null )
            return "redirect:/login";

        if( ! usersRepo.getByEmail( email ).getHashcode().equals( hashcode ) )
            return "redirect:/login";

        session.setAttribute("currentUser", usersRepo.getByEmail( email ) );
        model.addAttribute("session", session );

        return "redirect:/";
    }

    //Выход из сети
    @GetMapping("/logout")
    public String logout(HttpSession session)
    {
        session.setAttribute("currentUser", null );
        return "redirect:/";
    }

    @GetMapping("/reg")
    public String loadRegisatrationPage()
    {
        return "RegistrationPage";
    }

    @PostMapping("/reg")
    public String afterRegisatration(HttpSession session, @RequestParam String name,
                                     @RequestParam String email,@RequestParam String hashcode ) throws SQLException
    {
        Connection con = dataSource.getConnection();
        Statement stm = con.createStatement();

        if( usersRepo.getByEmail(email) != null )
            return "redirect:/reg";

        stm.executeUpdate( String.format("insert into users (name, email, hashcode, iconname) values ('%s','%s','%s', 'icon_placeholder.jpg')",
                name, email, hashcode) );
        con.close();

        return "redirect:/login";
    }



}
