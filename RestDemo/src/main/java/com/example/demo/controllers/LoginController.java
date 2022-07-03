package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.demo.database.UsersRepo;
import javax.servlet.http.HttpSession;
import com.example.demo.models.User;
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

    @PostMapping("/loginUser")
    public User loginUser(HttpSession session, @RequestParam String email,
                          @RequestParam String hashcode)
    {
        if( usersRepo.getByEmail( email ) == null )
            return null;

        if( ! usersRepo.getByEmail( email ).getHashcode().equals( hashcode ) )
            return null;

        session.setAttribute("currentUser", usersRepo.getByEmail( email ) );

        return  usersRepo.getByEmail( email );
    }

    //Выход из сети
    @PostMapping("/logout")
    public boolean logout(HttpSession session)
    {
        session.setAttribute("currentUser", null );
        return true;
    }

    @PostMapping("/registerUser")
    public boolean registerUser(@RequestParam String name, @RequestParam String email,
                                @RequestParam String hashcode ) throws SQLException
    {
        Connection con = dataSource.getConnection();
        Statement stm = con.createStatement();

        if( usersRepo.getByEmail(email) != null )
            return false;

        stm.executeUpdate( String.format("insert into users (name, email, hashcode, iconname) values ('%s','%s','%s', 'icon_placeholder.jpg')",
                name, email, hashcode) );
        con.close();

        return true;
    }
}
