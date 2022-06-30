package com.example.demo.database;

import com.example.demo.models.Users;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepo extends CrudRepository<Users, Integer>
{
    @Query("select * from Users where name = :nameOfUser")
    Users getByName(@Param("nameOfUser") String nameOfUser);

    @Query("select * from Users where email = :emailOfUser")
    Users getByEmail(@Param("emailOfUser") String email);

}
