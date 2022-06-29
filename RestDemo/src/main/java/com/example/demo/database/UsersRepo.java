package com.example.demo.database;

import com.example.demo.models.Users;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepo extends CrudRepository<Users, Integer>
{
    @Query("select * from Users where name = :nameOfUser")
    public Users getByName(@Param("nameOfUser") String nameOfUser);

    @Query("select * from Users where email = :emailOfUser")
    public Users getByEmail(@Param("emailOfUser") String email);

}
