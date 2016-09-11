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

    @RequestMapping("filtered-games")
    public List<Map<String, Object>> getGames(@RequestParam int gameType) {
        return gamesRepository.getFilteredGames(gameType);
    }
}
