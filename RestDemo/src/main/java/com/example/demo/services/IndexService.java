package com.example.demo.services;

import com.example.demo.database.BannedRepo;
import com.example.demo.database.GamesRepo;
import com.example.demo.database.RolesRepo;
import com.example.demo.database.UsersRepo;
import com.example.demo.models.Banned;
import com.example.demo.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Service
@Qualifier("indexService")
public class IndexService
{
    /*
    @Autowired
    ApplicationContext context;

    @Autowired
    BannedRepo bannedRepo;

    @Autowired
    GamesRepo gamesRepo;

    public List<Integer> getAllAdminsId()
    {
        return context.getBean(RolesRepo.class).getAllAdmins();
    }

    public void unban(int id) throws SQLException
    {
        DataSource dataSource = context.getBean( DataSource.class );
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate("delete from banned where idofuser=" + id);
        connection.close();
    }

     */

}
