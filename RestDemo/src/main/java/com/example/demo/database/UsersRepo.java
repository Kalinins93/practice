package com.example.demo.database;

import com.example.demo.models.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("usersRepo")
public interface UsersRepo extends CrudRepository<User, Integer>
{
    @Query("select * from users where name = :nameOfUser")
    User getByName(@Param("nameOfUser") String nameOfUser);

    @Query("select * from users where email = :emailOfUser")
    User getByEmail(@Param("emailOfUser") String email);
}
