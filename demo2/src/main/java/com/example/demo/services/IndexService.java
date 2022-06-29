package com.example.demo.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("indexService")
public class IndexService
{
/*
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
