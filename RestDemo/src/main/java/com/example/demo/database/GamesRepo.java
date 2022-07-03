package com.example.demo.database;

import com.example.demo.models.Game;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("gamesRepo")
public interface GamesRepo extends CrudRepository<Game, Integer>
{
    @Query("select * from games where id = :idOfGame")
    Game getById(@Param("idOfGame") int idOfGame);

    @Query("select * from games where id IN ( select idofgame from Libraries where idofuser = :userId )")
    List<Game> getAllGamesOfUser(@Param("userId") int idOfUser);

    @Query("select max(id) from games")
    int getMaxId();

    @Query("select imagename from games where id = :idOfGame")
    String getImageNameById(@Param("idOfGame") int id);

    @Query("select * from games where title like concat('%',:keyword,'%')")
    List<Game> findAllByKeyword(@Param("keyword") String keyword);

}
