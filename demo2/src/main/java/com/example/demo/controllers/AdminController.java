package com.example.demo.controllers;

import com.example.demo.database.BannedRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import com.example.demo.database.RolesRepo;
import com.example.demo.database.UsersRepo;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.example.demo.models.Users;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Controller
public class AdminController
{
    @Autowired
    RolesRepo rolesRepo;

    @Autowired
    DataSource dataSource;

    @Autowired
    UsersRepo usersRepo;

    @Autowired
    BannedRepo bannedRepo;

    @GetMapping("/admin")
    public String loadAdminPage(HttpSession session)
    {
        Users usr = (Users) session.getAttribute("currentUser");
        if( usr == null || ! rolesRepo.isAdmin( usr.getId() ) )
            return "redirect:/";

        if ( bannedRepo.isBanned( usr.getId() ) )
            return "redirect:/ban";

        return "adminPage";
    }

    @GetMapping("/allUsers")
    public String loadAllUsersPage(HttpSession session, Model model)
    {
        Users usr = (Users) session.getAttribute("currentUser");
        if( usr == null || ! rolesRepo.isAdmin( usr.getId() ) )
            return "redirect:/";

        if ( bannedRepo.isBanned( usr.getId() ) )
            return "redirect:/ban";

        model.addAttribute("allUsers", usersRepo.findAll());

        return "allUsersPage";
    }

    @GetMapping("/unbanUser")
    public String unbanUser(@RequestParam int id) throws SQLException
    {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate("delete from banned where idofuser=" + id);
        connection.close();
        return "redirect:/allUsers";
    }

    @GetMapping("/banUser")
    public String banUser(@RequestParam int id) throws SQLException
    {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate("insert into banned (idofuser) values (" + id + ")");
        connection.close();
        return "redirect:/allUsers";
    }

    @GetMapping("/grantAdmin")
    public String grantAdmin(@RequestParam int id) throws SQLException
    {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate("insert into Roles (idofuser, role) values (" + id + ", 'admin')");
        connection.close();
        return "redirect:/allUsers";
    }

    @GetMapping("/revokeAdmin")
    public String revokeAdmin(@RequestParam int id) throws SQLException
    {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate("update Roles set role = '' where idofuser =" + id);
        connection.close();
        return "redirect:/allUsers";
    }


}
