package net.wittigchavey.db;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Rachel on 9/13/2016.
 */
public class GameDto {

    private Integer id;
    private String name;
    private Integer typeID;
    private Integer minPlayers;
    private Integer maxPlayers;
    private Integer lengthMinutes;
    private List<Integer> locationIDs;
    private LocalDate  lastPlayed;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTypeID() {
        return typeID;
    }

    public void setTypeID(Integer typeID) {
        this.typeID = typeID;
    }

    public Integer getMinPlayers() {
        return minPlayers;
    }

    public void setMinPlayers(Integer minPlayers) {
        this.minPlayers = minPlayers;
    }

    public Integer getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(Integer maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public Integer getLengthMinutes() {
        return lengthMinutes;
    }

    public void setLengthMinutes(Integer lengthMinutes) {
        this.lengthMinutes = lengthMinutes;
    }

    public List<Integer> getLocationIDs() {
        return locationIDs;
    }

    public void setLocationIDs(List<Integer> locationIDs) {
        this.locationIDs = locationIDs;
    }

    public LocalDate getLastPlayed() {
        return lastPlayed;
    }

    public void setLastPlayed(LocalDate lastPlayed) {
        this.lastPlayed = lastPlayed;
    }
}
