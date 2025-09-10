package com.github.Shimado;

import com.github.Shimado.enums.ERecords;
import com.github.Shimado.interfaces.IBoomboxSong;
import org.bukkit.Location;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

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
    SoundPlayerAPI setBoomboxInstanceUUID(UUID boomboxInstanceUUID);
    @Nullable
    IBoomboxSong getCurrentSong();
    SoundPlayerAPI setCurrentSong(IBoomboxSong boomboxSong);
    SoundPlayerAPI setSongsToList(List<IBoomboxSong> songsToPlay);
    SoundPlayerAPI addSongToList(IBoomboxSong boomboxSong);
    SoundPlayerAPI removeSongFromList(String songID);
    SoundPlayerAPI setLocToPlay(Location locToPlay);
    SoundPlayerAPI setPlayerToPlay(Player playerToPlay);
    SoundPlayerAPI removePlayerFromPlay();
    SoundPlayerAPI setAcceleration(int acceleration);
    SoundPlayerAPI setVolume(int volume);
    boolean isAutoPlay();
    SoundPlayerAPI enableAutoPlay(boolean isAutoPlay);
    boolean isAuditions();
    SoundPlayerAPI enableAuditions(boolean isAuditions);
    @Nullable
    BossBar getBossBar();
    SoundPlayerAPI setBossBar(BossBar bossBar);
    boolean nextSong();
    boolean previousSong();
    void play();
    void stop(boolean stopSound);
    void stopAndClear();

}
