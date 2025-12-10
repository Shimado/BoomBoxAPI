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
     * Sets the unique identifier for this song.
     *
     * @param id the new song ID
     */

    void setID(@NotNull String id);

    /**
     * Gets the filename associated with this song.
     * This refers to an NBS file.
     *
     * @return the filename
     */

    @Nullable
    String getFilename();

    /**
     * Sets the NBS filename associated with this song.
     *
     * @param filename the new filename
     */

    void setFilename(@Nullable String filename);

    /**
     * Gets the material used to represent this song when it hasn't been purchased.
     *
     * @return the material name for unpurchased state
     */

    @Nullable
    String getMaterialUnPurchased();

    /**
     * Sets the material used to represent this song when it hasn't been purchased.
     *
     * @param materialUnPurchased the material name for unpurchased state
     */

    void setMaterialUnPurchased(@Nullable String materialUnPurchased);

    /**
     * Gets the custom model data for the unpurchased song item.
     * Used for custom texture differentiation.
     *
     * @return the custom model data value for unpurchased state
     */

    int getCustomModelDataUnPurchased();

    /**
     * Sets the custom model data for the unpurchased song item.
     *
     * @param customModelDataUnPurchased the custom model data value for unpurchased state
     */

    void setCustomModelDataUnPurchased(int customModelDataUnPurchased);

    /**
     * Gets the material used to represent this song when it has been purchased.
     *
     * @return the material name for purchased state
     */

    @Nullable
    String getMaterialPurchased();

    /**
     * Sets the material used to represent this song when it has been purchased.
     *
     * @param materialPurchased the material name for purchased state
     */

    void setMaterialPurchased(@Nullable String materialPurchased);

    /**
     * Gets the custom model data for the purchased song item.
     * Used for custom texture differentiation.
     *
     * @return the custom model data value for purchased state
     */

    int getCustomModelDataPurchased();

    /**
     * Sets the custom model data for the purchased song item.
     *
     * @param customModelDataPurchased the custom model data value for purchased state
     */

    void setCustomModelDataPurchased(int customModelDataPurchased);

    /**
     * Gets the material used when this song appears in a playlist.
     *
     * @return the material name for playlist display
     */

    @Nullable
    String getMaterialPlaylist();

    /**
     * Sets the material used when this song appears in a playlist.
     *
     * @param materialPlaylist the material name for playlist display
     */

    void setMaterialPlaylist(@Nullable String materialPlaylist);

    /**
     * Gets the custom model data for the playlist song item.
     * Used for custom texture differentiation in playlists.
     *
     * @return the custom model data value for playlist display
     */

    int getCustomModelDataPlaylist();

    /**
     * Sets the custom model data for the playlist song item.
     *
     * @param customModelDataPlaylist the custom model data value for playlist display
     */

    void setCustomModelDataPlaylist(int customModelDataPlaylist);

    /**
     * Gets the display name of this song.
     *
     * @return the song name
     */

    @Nullable
    String getName();

    /**
     * Sets the display name of this song.
     *
     * @param name the new song name
     */

    void setName(@Nullable String name);

    /**
     * Gets the description of this song.
     * Typically displayed as lore in item tooltips.
     *
     * @return the song description as a list of strings
     */

    @NotNull
    List<String> getDescription();

    /**
     * Sets the description of this song.
     *
     * @param description the new song description as a list of strings
     */

    void setDescription(@NotNull List<String> description);

    /**
     * Gets the purchase price of this song.
     *
     * @return the price in the game's currency
     */

    double getPrice();

    /**
     * Sets the purchase price of this song.
     *
     * @param price the new price in the game's currency
     */

    void setPrice(double price);

    /**
     * Gets the permission required to access this song.
     *
     * @return the permission node
     */

    @Nullable
    String getPermission();

    /**
     * Sets the permission required to access this song.
     *
     * @param permission the permission node
     */

    void setPermission(@Nullable String permission);

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
     * Sets the custom OGG sound name for this boombox song.
     *
     * @param customOggSoundName the custom OGG sound name
     */

    void setCustomOggSoundName(@Nullable String customOggSoundName);

    /**
     * Gets the length of the custom OGG sound in seconds.
     *
     * @return the custom OGG sound length
     */

    int getCustomOggSoundLength();

    /**
     * Sets the length of the custom OGG sound in seconds.
     *
     * @param customOggSoundLength the custom OGG sound length
     */

    void setCustomOggSoundLength(int customOggSoundLength);

}
