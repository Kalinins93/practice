package com.example.demo.database;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.demo.models.Banned;

@Repository
@Qualifier("bannedRepo")
public interface BannedRepo extends CrudRepository<Banned, Integer>
{
    @Query("select EXISTS (select * from banned where idofuser = :userid)")
    boolean isBanned(@Param("userid") int id);

    @Query("select * from banned where idofuser = :userid")
    Banned getBanned(@Param("userid") int id);
}
