package net.wittigchavey.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * Created by Rachel on 7/26/2016.
 */
@Repository
public class GamesRepository {

    private final JdbcOperations jdbcOperations;
    private final SimpleJdbcInsert gameInsert;

    @Autowired
    public GamesRepository(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcOperations = jdbcTemplate;
        this.gameInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("game").usingGeneratedKeyColumns("id");
    }

    public List<GameDto> getAllGames() {
        List<GameDto> games = jdbcOperations.query("Select * from game Order By name", BeanPropertyRowMapper.newInstance(GameDto.class));
        games.forEach(game -> game.setLocationIDs(getLocationIds(game.getId())));
        return games;
    }

    private List<Integer> getLocationIds(Integer gameId) {

        return jdbcOperations.queryForList("select locationID from game_location where gameID = ?", Integer.class, gameId);
    }

    //add parameters
    public List<GameDto> getFilteredGames(int numPlayers, int type, int location, int length) {
        List<GameDto> games = jdbcOperations.query("Select * from game " +
                " join game_location gl on game.id = gl.gameID" +
                " where ? between minPlayers and maxPlayers and typeID = ? and locationID = ? and lengthMinutes <= ?" +
                        " Order By lastPlayed",
                BeanPropertyRowMapper.newInstance(GameDto.class),
                numPlayers, type, location, length);
        games.forEach(game -> game.setLocationIDs(getLocationIds(game.getId())));
        return games;
    }

    public void addGame(NewGameDto newGameDto) {

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("name", newGameDto.getName())
                .addValue("typeID", newGameDto.getType())
                .addValue("minPlayers", newGameDto.getMinPlayers())
                .addValue("maxPlayers", newGameDto.getMaxPlayers())
                .addValue("lengthMinutes", newGameDto.getLength());

        Number gameId = gameInsert.executeAndReturnKey(params);
        for (Integer location : newGameDto.getLocations()) {
            jdbcOperations.update("insert into game_location (gameID, locationID) values (?, ?)", gameId, location);
        }
    }

    public List<Map<String, Object>> getAllTypes() {
        return jdbcOperations.queryForList("Select * from type");
    }

    public List<Map<String, Object>> getAllLocations() {
        return jdbcOperations.queryForList("Select * from location");
    }
}


