package com.example.demo.database;

import com.example.demo.models.Games;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("gamesRepo")
public interface GamesRepo extends CrudRepository<Games, Integer>
{
    @Query("select * from Games where id = :idOfGame")
    Games getById(@Param("idOfGame") int idOfGame);

    @Query("select * from Games where id IN ( select idofgame from Libraries where idofuser = :userId )")
    List<Games> getAllGamesOfUser(@Param("userId") int idOfUser);

    @Query("insert into Games (title, description, price) values (:titleParam, :descriptionParam, :priceParam)")
    int insert(@Param("titleParam") String title, @Param("descriptionParam") String description, @Param("priceParam") int price);

    @Query("select max(id) from Games")
    int getMaxId();

    @Query("select imagename from Games where id = :idOfGame")
    String getImageNameById(@Param("idOfGame") int id);

    @Query("select * from Games where title like concat('%',:keyword,'%')")
    List<Games> findAllByKeyword(@Param("keyword") String keyword);

}
