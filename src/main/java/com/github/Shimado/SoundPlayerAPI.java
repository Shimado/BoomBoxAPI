package com.github.Shimado;

import com.github.Shimado.interfaces.BoomboxSong;
import org.bukkit.Location;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public interface SoundPlayerAPI {

    /**
     * Example:
     * SoundPlayerAPI soundPlayer = new SoundPlayerImpl(plugin)
     *                 .setBoomboxInstance(this)
     *                 .setVolume(volume)
     *                 .setAcceleration(acceleration)
     *                 .setLocToPlay(loc)
     *                 .setCurrentSong(soundList.get(0))
     *                 .setSongsToList(soundList)
     *                 .setBossBar(bossBar)
     *                 .play();
     * **/

    int getFullTimeInSeconds();
    int getTimeInSeconds();
    boolean isPlaying();
    @Nullable
    UUID getBoomboxInstanceUUID();
    SoundPlayerAPI setBoomboxInstanceUUID(@Nullable UUID boomboxInstanceUUID);
    @Nullable
    BoomboxSong getCurrentSong();
    SoundPlayerAPI setCurrentSong(@Nullable BoomboxSong boomboxSong);
    SoundPlayerAPI setSongsToList(@Nonnull List<BoomboxSong> songsToPlay);
    SoundPlayerAPI addSongToList(@Nonnull BoomboxSong boomboxSong);
    SoundPlayerAPI removeSongFromList(@Nonnull String songID);
    SoundPlayerAPI setLocToPlay(@Nullable Location locToPlay);
    @Nullable
    Player getPlayerToPlay();
    SoundPlayerAPI setPlayerToPlay(@Nullable Player playerToPlay);
    SoundPlayerAPI setAcceleration(int acceleration);
    SoundPlayerAPI setVolume(int volume);
    boolean isAutoPlay();
    SoundPlayerAPI enableAutoPlay(boolean isAutoPlay);
    boolean isAuditions();
    SoundPlayerAPI enableAuditions(boolean isAuditions);
    @Nullable
    BossBar getBossBar();
    SoundPlayerAPI setBossBar(@Nullable BossBar bossBar);
    boolean nextSong();
    boolean previousSong();
    void play();
    void stop(boolean stopSound);
    void stopAndClear();

}
