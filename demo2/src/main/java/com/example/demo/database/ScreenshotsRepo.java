package com.example.demo.database;

import com.example.demo.models.Screenshots;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScreenshotsRepo extends CrudRepository<Screenshots, Integer>
{
    @Query("select screenshotname from Screenshots where idofgame = :gameId")
    public List<String> getNamesByGameId(@Param("gameId") int ifOfGame);

    @Query("insert into Screenshots (idofgame, screenshotname) values (:gameId, :screenshotName)")
    public int insert(@Param("gameId") int ifOfGame, @Param("screenshotName") String screenshotName);

    @Query("select max(id) from Screenshots")
    public int getMaxId();

    @Query("update Screenshots set screenshotname = :newName where id = :thisImageId")
    public int updateScreenshotName(@Param("newName") String newName,@Param("thisImageId") int id );

}
