package net.wittigchavey.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by Rachel on 7/25/2016.
 */
@RestController
public class GamesController {

    @Autowired
    private GamesRepository gamesRepository;

    @RequestMapping("games")
    public List<Map<String, Object>> getGames() {
        return gamesRepository.getAllGames();
    }

    //deal with when we don't have all parameters
    @RequestMapping("filtered-games")
    public List<Map<String, Object>> getGames(@RequestParam int numPlayers, @RequestParam int type, @RequestParam int location, @RequestParam int length) {
        return gamesRepository.getFilteredGames(numPlayers, type, location, length);
    }

    @RequestMapping("addGame")
    public void addGame(@RequestParam String name, @RequestParam int type, @RequestParam int minPlayers, @RequestParam int maxPlayers, @RequestParam int length, @RequestParam int location) {
        gamesRepository.addGame(name, type, minPlayers, maxPlayers, length, location);
    }
}
