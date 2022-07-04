package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.demo.database.UsersRepo;
import org.springframework.web.bind.annotation.RestController;
import java.sql.SQLException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;

@RestController
public class LoginController
{
    @Autowired
    DataSource dataSource;

    @Autowired
    UsersRepo usersRepo;

    @PostMapping("/registerUser")
    public boolean registerUser(@RequestParam String name, @RequestParam String email,
                                @RequestParam String hashcode )
    {
        if( usersRepo.getByEmail(email) != null ) return false;

        try
        {
            Connection con = dataSource.getConnection();
            Statement stm = con.createStatement();

            stm.executeUpdate(String.format("insert into users (name, email, hashcode, iconname) " +
                            "values ('%s','%s','%s', 'icon_placeholder.jpg')",
                    name, email, hashcode));
            con.close();
        }
        catch (SQLException e){}

        return true;
    }
}
