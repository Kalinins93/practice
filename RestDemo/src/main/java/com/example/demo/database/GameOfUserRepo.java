package com.example.demo.database;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.example.demo.models.GameOfUser;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository("gameOfUserRepo")
public interface GameOfUserRepo extends CrudRepository<GameOfUser,Integer>
{
    @Query("select * from Libraries where id = :idOfUser")
    List<GameOfUser> getAllByUserId( @Param("idOfUser") int idOfUser );

    @Query("select exists( select * from Libraries where (idofuser = :idOfUser AND idofgame = :idOfGame) )")
    boolean contains( @Param("idOfUser") int idOfUser, @Param("idOfGame") int idOfGame );

}
