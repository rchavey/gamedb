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
}


