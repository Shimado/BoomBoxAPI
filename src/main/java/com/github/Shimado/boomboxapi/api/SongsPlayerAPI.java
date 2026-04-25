package com.github.Shimado.boomboxapi.api;

import com.github.Shimado.boomboxapi.instances.BoomboxSong;
import org.bukkit.Location;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public interface SongsPlayerAPI {

    /**
     * Example:
     * SongsPlayerAPI songsPlayer = new SongsPlayerImpl(plugin)
     *                 .setVolume(volume)
     *                 .setAcceleration(acceleration)
     *                 .enableAuditions(true)
     *                 .enableAutoPlay(true)
     *                 .setLocToPlay(loc)
     *                 .setCurrentSong(playlistList.get(0))
     *                 .setSongsToList(playlistList)
     *                 .setBossBar(bossBar)
     *                 .play()
     */

    boolean isOggSong();

    int getFullTimeInSeconds();

    int getTimeInSeconds();

    boolean isPlaying();

    @Nullable
    UUID getBoomboxInstanceUUID();

    SongsPlayerAPI setBoomboxInstanceUUID(@Nullable UUID boomboxInstanceUUID);

    @Nullable
    BoomboxSong getCurrentSong();

    SongsPlayerAPI setCurrentSong(@Nullable BoomboxSong boomboxSong);

    SongsPlayerAPI setSongsToList(@NotNull List<BoomboxSong> songsToPlay);

    SongsPlayerAPI addSongToList(@NotNull BoomboxSong boomboxSong);

    SongsPlayerAPI removeSongFromList(@NotNull String songID);

    SongsPlayerAPI setLocToPlay(@Nullable Location locToPlay);

    @Nullable
    Player getPlayerToPlay();

    SongsPlayerAPI setPlayerToPlay(@Nullable Player playerToPlay);

    SongsPlayerAPI removePlayerFromPlay();

    SongsPlayerAPI setAcceleration(int acceleration);

    SongsPlayerAPI setVolume(int volume);

    boolean isAutoPlay();

    SongsPlayerAPI enableAutoPlay(boolean isAutoPlay);

    boolean isAuditions();

    SongsPlayerAPI enableAuditions(boolean isAuditions);

    @Nullable
    BossBar getBossBar();

    SongsPlayerAPI setBossBar(@Nullable BossBar bossBar);

    boolean nextSong();

    boolean previousSong();

    void play();

    void stop(boolean stopSound);

    void stopAndClear();

}
