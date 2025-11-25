package com.github.Shimado;

import com.github.Shimado.interfaces.BoomboxSong;
import org.bukkit.Location;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public interface NbsPlayerAPI {

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
    NbsPlayerAPI setBoomboxInstanceUUID(@Nullable UUID boomboxInstanceUUID);
    @Nullable
    BoomboxSong getCurrentSong();
    NbsPlayerAPI setCurrentSong(@Nullable BoomboxSong boomboxSong);
    NbsPlayerAPI setSongsToList(@Nonnull List<BoomboxSong> songsToPlay);
    NbsPlayerAPI addSongToList(@Nonnull BoomboxSong boomboxSong);
    NbsPlayerAPI removeSongFromList(@Nonnull String songID);
    NbsPlayerAPI setLocToPlay(@Nullable Location locToPlay);
    @Nullable
    Player getPlayerToPlay();
    NbsPlayerAPI setPlayerToPlay(@Nullable Player playerToPlay);
    NbsPlayerAPI removePlayerFromPlay();
    NbsPlayerAPI setAcceleration(int acceleration);
    NbsPlayerAPI setVolume(int volume);
    boolean isAutoPlay();
    NbsPlayerAPI enableAutoPlay(boolean isAutoPlay);
    boolean isAuditions();
    NbsPlayerAPI enableAuditions(boolean isAuditions);
    @Nullable
    BossBar getBossBar();
    NbsPlayerAPI setBossBar(@Nullable BossBar bossBar);
    boolean nextSong();
    boolean previousSong();
    void play();
    void stop();
    void stopAndClear();

}
