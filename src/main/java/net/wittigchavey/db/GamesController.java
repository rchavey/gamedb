package net.wittigchavey.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Rachel on 7/25/2016.
 */
@RestController
public class GamesController {

    @Autowired
    private GamesRepository gamesRepository;

    @RequestMapping("games")
    public List<GameDto> getGames() {
        return gamesRepository.getAllGames();
    }

    //deal with when we don't have all parameters
    @RequestMapping("filtered-games")
    public List<GameDto> getGames(@RequestParam int numPlayers, @RequestParam int type, @RequestParam int location, @RequestParam int length) {
        return gamesRepository.getFilteredGames(numPlayers, type, location, length);
    }

    @RequestMapping(value = "addGame", method = RequestMethod.POST)
    public void addGame(@RequestBody NewGameDto newGameDto) {
        gamesRepository.addGame(newGameDto);
    }
}
