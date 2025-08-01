package com.github.Shimado;

import com.github.Shimado.enums.ERecords;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public interface RecordsPlayerAPI {

    /**
     * Example:
     * RecordsPlayerAPI recordsPlayer = new RecordsPlayerImpl(plugin)
     *                 .setBoomboxInstance(this)
     *                 .setVolume(volume)
     *                 .setAcceleration(acceleration)
     *                 .setLocToPlay(loc)
     *                 .setCurrentSong(recordsList.get(0))
     *                 .setSongsToList(recordsList)
     *                 .setBossBar(bossBar)
     *                 .play();
     * **/

    int getFullTimeInSeconds();
    int getTimeInSeconds();
    boolean isPlaying();
    @Nullable
    UUID getBoomboxInstanceUUID();
    NbsPlayerAPI setBoomboxInstanceUUID(UUID boomboxInstanceUUID);
    @Nullable
    ERecords getCurrentSong();
    RecordsPlayerAPI setCurrentSong(ERecords record);
    RecordsPlayerAPI setSongsToList(List<ERecords> recordsToPlay);
    RecordsPlayerAPI addSongToList(ERecords record);
    RecordsPlayerAPI removeSongFromList(int recordID);
    RecordsPlayerAPI setLocToPlay(Location locToPlay);
    RecordsPlayerAPI setPlayerToPlay(Player playerToPlay);
    RecordsPlayerAPI removePlayerFromPlay();
    RecordsPlayerAPI setAcceleration(int acceleration);
    RecordsPlayerAPI setVolume(int volume);
    @Nullable
    BossBar getBossBar();
    RecordsPlayerAPI setBossBar(BossBar bossBar);
    boolean nextSong();
    boolean previousSong();
    void play();
    void stop(boolean stopSound);

}
