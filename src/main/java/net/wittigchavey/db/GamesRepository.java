package net.wittigchavey.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Rachel on 7/26/2016.
 */
@Repository
public class GamesRepository {
    @Autowired
    private JdbcOperations jdbcOperations;

    public List<Map<String, Object>> getAllGames() {
        return jdbcOperations.queryForList("Select * from game");
    }

    //add parameters
    public List<Map<String, Object>> getFilteredGames(int numPlayers, int type, int location, int length) {
        return jdbcOperations.queryForList("Select * from game " +
                "where ? between minPlayers and maxPlayers and typeID = ? and locationId = ? and lengthMinutes >= ?",
                numPlayers, type, location, length);
    }
}


