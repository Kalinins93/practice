package com.example.demo.database;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.demo.models.Roles;
import java.util.List;

@Repository("rolesRepo")
public interface RolesRepo extends CrudRepository<Roles, Integer>
{
    @Query("select idofuser from roles where role = 'admin'")
    List<Integer> getAllAdmins();

    @Query("select role from roles where idofuser = :userId ")
    String getUserRole(@Param("userId") int id);

    @Query("select exists ( select * from roles where idofuser = :userid and role = 'admin' )")
    boolean isAdmin(@Param("userid") int id);
}
