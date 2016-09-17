package net.wittigchavey.db;

import java.time.LocalDate;

public class LastPlayedDateUpdateDto {

    private LocalDate lastPlayed;

    public LocalDate getLastPlayed() {

        return lastPlayed;
    }

    public void setLastPlayed(LocalDate lastPlayed) {

        this.lastPlayed = lastPlayed;
    }
}
