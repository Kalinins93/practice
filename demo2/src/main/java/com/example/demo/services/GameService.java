package com.example.demo.services;

import com.example.demo.models.Game;
import com.example.demo.requestentitys.GameRequest;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;

@Service("gameService")
public class GameService
{
    public List<Game> getAllGames()
    {
        List<Game> games = new ArrayList<>();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate restTemplate = new RestTemplate();
        try
        {
            HttpEntity<GameRequest> entity = new HttpEntity<>(headers);
            ResponseEntity<? extends List> responseGames = restTemplate.exchange("http://localhost:8081/getAllGames",
                    HttpMethod.POST, entity, games.getClass() );
            games = responseGames.getBody();
        }
        catch (Exception e){
            throw e;
        }
        return games;
    }

    public Game getGameById( int gameId )
    {
        Game game = new Game();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Game> entity = new HttpEntity<>(game, headers);
        RestTemplate restTemplate = new RestTemplate();
        try
        {
            ResponseEntity<Game> responseGames = restTemplate.exchange(
            "http://localhost:8081/getGame",
                    HttpMethod.POST, entity, Game.class, gameId  );
            game = responseGames.getBody();
        }
        catch (Exception e){
            //throw e;
        }
        return game;
    }

    public List<String> getScreenshotsNames(int id)
    {
        List<String> names = new ArrayList<>();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate restTemplate = new RestTemplate();
        try
        {
            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity< ? extends List > response = restTemplate.exchange("http://localhost:8081/getGameScreenshots",
                    HttpMethod.POST, entity, names.getClass(), id );
            names = response.getBody();
        }
        catch (Exception e){}

        return names;
    }

}
