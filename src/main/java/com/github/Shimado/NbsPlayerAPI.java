package com.github.Shimado;

import com.github.Shimado.interfaces.IBoomboxSong;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public interface NbsPlayerAPI<S extends IBoomboxSong> {

    /**
     * Example:
     * NbsPlayerAPI nbsPlayer = new NbsPlayerImpl(plugin)
     *                 .setBoomboxInstance(this)
     *                 .setVolume(volume)
     *                 .setAcceleration(acceleration)
     *                 .enableAuditions(true)
     *                 .enableAutoPlay(true)
     *                 .setLocToPlay(loc)
     *                 .setCurrentSong(playlistList.get(0))
     *                 .setSongsToList(playlistList)
     *                 .setBossBar(bossBar)
     *                 .play()
     * **/

    int getFullTimeInSeconds();
    int getTimeInSeconds();
    boolean isPlaying();
    @Nullable
    UUID getBoomboxInstanceUUID();
    NbsPlayerAPI setBoomboxInstanceUUID(UUID boomboxInstanceUUID);
    @Nullable
    S getCurrentSong();
    NbsPlayerAPI setCurrentSong(S boomboxSong);
    NbsPlayerAPI setSongsToList(List<S> songsToPlay);
    NbsPlayerAPI addSongToList(S boomboxSong);
    NbsPlayerAPI removeSongFromList(String songID);
    NbsPlayerAPI setLocToPlay(Location locToPlay);
    NbsPlayerAPI setPlayerToPlay(Player playerToPlay);
    NbsPlayerAPI removePlayerFromPlay();
    NbsPlayerAPI setAcceleration(int acceleration);
    NbsPlayerAPI setVolume(int volume);
    boolean isAutoPlay();
    NbsPlayerAPI enableAutoPlay(boolean isAutoPlay);
    boolean isAuditions();
    NbsPlayerAPI enableAuditions(boolean isAuditions);
    @Nullable
    BossBar getBossBar();
    NbsPlayerAPI setBossBar(BossBar bossBar);
    boolean nextSong();
    boolean previousSong();
    void play();
    void stop();
    void stopAndClear();

}
