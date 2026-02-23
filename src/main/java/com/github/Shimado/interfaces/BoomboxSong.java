package com.github.Shimado.interfaces;

import com.github.Shimado.nbs.NbsSong;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Represents a playable song that can be used in the game.
 * This interface defines the complete contract for boombox songs, including their
 * metadata, visual representation, purchase status, and audio data.
 *
 * Boombox songs can be either NBS-based (Note Block Studio format) or custom OGG audio files,
 * providing flexibility for different types of musical content.
 */

public interface BoomboxSong {

    /**
     * Gets the unique identifier for this song.
     *
     * @return the song ID
     */

    @NotNull
    String getID();


    /**
     * Gets the filename associated with this song.
     * This refers to an NBS file.
     *
     * @return the filename
     */

    @Nullable
    String getFilename();


    /**
     * Gets the material used to represent this song when it hasn't been purchased.
     *
     * @return the material name for unpurchased state
     */

    @Nullable
    String getMaterialUnPurchased();

    /**
     * Gets the custom model data for the unpurchased song item.
     * Used for custom texture differentiation.
     *
     * @return the custom model data value for unpurchased state
     */

    int getCustomModelDataUnPurchased();


    /**
     * Gets the material used to represent this song when it has been purchased.
     *
     * @return the material name for purchased state
     */

    @Nullable
    String getMaterialPurchased();

    /**
     * Gets the custom model data for the purchased song item.
     * Used for custom texture differentiation.
     *
     * @return the custom model data value for purchased state
     */

    int getCustomModelDataPurchased();


    /**
     * Gets the material used when this song appears in a playlist.
     *
     * @return the material name for playlist display
     */

    @Nullable
    String getMaterialPlaylist();

    /**
     * Gets the custom model data for the playlist song item.
     * Used for custom texture differentiation in playlists.
     *
     * @return the custom model data value for playlist display
     */

    int getCustomModelDataPlaylist();


    /**
     * Gets the display name of this song.
     *
     * @return the song name
     */

    @Nullable
    String getName();


    /**
     * Gets the description of this song.
     * Typically displayed as lore in item tooltips.
     *
     * @return the song description as a list of strings
     */

    @NotNull
    List<String> getDescription();


    /**
     * Gets the purchase price of this song.
     *
     * @return the price in the game's currency
     */

    double getPrice();


    /**
     * Gets the permission required to access this song.
     *
     * @return the permission node
     */

    @Nullable
    String getPermission();


    /**
     * Gets the number of times this song has been owned/purchased.
     *
     * @return the ownership count
     */

    int getOwn();

    /**
     * Increments the ownership count by 1.
     * Equivalent to calling setOwn(getOwn() + 1).
     */

    void addOwn();

    /**
     * Sets the number of times this song has been owned/purchased.
     *
     * @param own the new ownership count
     */

    void setOwn(int own);


    /**
     * Gets the number of times this song has been played/listened to.
     *
     * @return the audition count
     */

    int getAuditions();

    /**
     * Increments the audition count by 1.
     * Equivalent to calling setAuditions(getAuditions() + 1).
     */

    void addAuditions();

    /**
     * Sets the number of times this song has been played/listened to.
     *
     * @param auditions the new audition count
     */

    void setAuditions(int auditions);


    /**
     * Gets the NBS song data for this boombox song.
     * Returns null if this song uses custom OGG audio instead.
     *
     * @return the NBS song data, or null if not an NBS-based song
     */

    @Nullable
    NbsSong getNbsSong();

    /**
     * Sets the NBS song data for this boombox song.
     *
     * @param nbsSong the NBS song data
     */

    void setNbsSong(@Nullable NbsSong nbsSong);


    /**
     * Gets the custom OGG sound name for this boombox song.
     * Returns null if this song uses NBS format instead.
     *
     * @return the custom OGG sound name, or null if not an OGG-based song
     */

    @Nullable
    String getCustomOggSoundName();


    /**
     * Gets the length of the custom OGG sound in seconds.
     *
     * @return the custom OGG sound length
     */

    int getCustomOggSoundLength();


}
