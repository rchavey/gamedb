package net.wittigchavey.db;

import org.springframework.beans.factory.annotation.Autowired;
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


    public List<Map<String, Object>> getAllGames() {
        return jdbcOperations.queryForList("Select * from game");
    }

    //add parameters
    public List<Map<String, Object>> getFilteredGames(int numPlayers, int type, int location, int length) {
        return jdbcOperations.queryForList("Select * from game " +
                "where ? between minPlayers and maxPlayers and typeID = ? and locationId = ? and lengthMinutes >= ?",
                numPlayers, type, location, length);
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
}


