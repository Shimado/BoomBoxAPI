package com.github.Shimado;

import com.github.Shimado.enums.ERecords;
import org.bukkit.Location;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
    RecordsPlayerAPI setBoomboxInstanceUUID(@Nullable UUID boomboxInstanceUUID);
    @Nullable
    ERecords getCurrentSong();
    RecordsPlayerAPI setCurrentSong(@Nullable ERecords record);
    RecordsPlayerAPI setSongsToList(@NotNull List<ERecords> recordsToPlay);
    RecordsPlayerAPI addSongToList(@NotNull ERecords record);
    RecordsPlayerAPI removeSongFromList(int recordID);
    RecordsPlayerAPI setLocToPlay(@Nullable Location locToPlay);
    @Nullable
    Player getPlayerToPlay();
    RecordsPlayerAPI setPlayerToPlay(@Nullable Player playerToPlay);
    RecordsPlayerAPI setAcceleration(int acceleration);
    RecordsPlayerAPI setVolume(int volume);
    @Nullable
    BossBar getBossBar();
    RecordsPlayerAPI setBossBar(@Nullable BossBar bossBar);
    boolean nextSong();
    boolean previousSong();
    void play();
    void stop(boolean stopSound);

}
