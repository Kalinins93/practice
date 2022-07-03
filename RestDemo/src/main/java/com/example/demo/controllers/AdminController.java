package com.example.demo.controllers;

import com.example.demo.database.BannedRepo;
import com.example.demo.models.Banned;
import com.example.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.database.RolesRepo;
import com.example.demo.database.UsersRepo;
import javax.sql.DataSource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@RestController
public class AdminController
{
    @Autowired
    BannedRepo bannedRepo;

    @Autowired
    DataSource dataSource;

    @Autowired
    UsersRepo usersRepo;

    @Autowired
    RolesRepo rolesRepo;

    @PostMapping("/getAllUsers")
    public List<User> getAllUsers()
    {
        return (List<User>) usersRepo.findAll();
    }

    @PostMapping("/getAllAdmins")
    public List<Integer> getAllAdmins()
    {
        return rolesRepo.getAllAdmins();
    }

    @PostMapping("/isAdmin")
    public boolean isAdmins(@RequestParam int id)
    {
        return rolesRepo.isAdmin(id);
    }

    @PostMapping("/getAllBanned")
    public List<Banned> getAllBanned()
    {
        return (List<Banned>) bannedRepo.findAll();
    }


    @PostMapping("/unbanUser")
    public String unbanUser(@RequestParam int id) throws SQLException
    {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate("delete from banned where idofuser=" + id);
        connection.close();
        return "redirect:/allUsers";
    }

    @PostMapping("/banUser")
    public String banUser(@RequestParam int id) throws SQLException
    {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate("insert into banned (idofuser) values (" + id + ")");
        connection.close();
        return "redirect:/allUsers";
    }

    @PostMapping("/grantAdmin")
    public String grantAdmin(@RequestParam int id) throws SQLException
    {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate("insert into roles (idofuser, role) values (" + id + ", 'admin')");
        connection.close();
        return "redirect:/allUsers";
    }

    @PostMapping("/revokeAdmin")
    public String revokeAdmin(@RequestParam int id) throws SQLException
    {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate("update roles set role = '' where idofuser =" + id);
        connection.close();
        return "redirect:/allUsers";
    }

}
