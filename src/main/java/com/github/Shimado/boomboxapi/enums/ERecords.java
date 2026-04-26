package com.github.Shimado.boomboxapi.enums;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

/**
 * Enum representing all available music discs in the game with their associated properties.
 * This enum provides a comprehensive mapping of music disc items to their material names,
 * sound identifiers, durations, and other metadata across different Minecraft versions.
 *
 * Each record contains compatibility mappings for different server implementations
 * and version naming conventions to ensure cross-version compatibility.
 */

public interface ERecords {


    /**
     * Gets the unique identifier for this music disc.
     *
     * @return the music disc ID
     */

    int getID();


    /**
     * Gets the duration of the music track in seconds.
     *
     * @return the duration in seconds
     */

    int getDuration();


    /**
     * Gets the material representation of this music disc.
     * This method attempts to find a valid material by trying each material name
     * in the materials array until a non-null material is found.
     *
     * @return the Material for this music disc, or Material.APPLE if no valid material is found
     */

    @NotNull
    Material getMaterial();


    /**
     * Gets the sound associated with this music disc.
     * This method attempts to find a valid sound by trying each sound name
     * in the sounds array until a non-null sound is found.
     *
     * @return the Sound for this music disc, or Sound.UI_TOAST_IN if no valid sound is found
     */

    @NotNull
    Sound getSound();


    @Nullable
    static <T extends Enum<T> & ERecords> T findByMaterial(@NotNull Class<T> enumClass, @Nullable Material material) {
        if(material == null) return null;
        return Arrays.stream(enumClass.getEnumConstants())
                .filter(e -> e.getMaterial().equals(material))
                .findFirst()
                .orElse(null);
    }


    @Nullable
    static <T extends Enum<T> & ERecords> T findByName(@NotNull Class<T> enumClass, @Nullable String name) {
        if(name == null) return null;
        return Arrays.stream(enumClass.getEnumConstants())
                .filter(e -> e.toString().equals(name))
                .findFirst()
                .orElse(null);
    }


    @Nullable
    static <T extends Enum<T> & ERecords> T findByID(@NotNull Class<T> enumClass, int id) {
        return Arrays.stream(enumClass.getEnumConstants())
                .filter(e -> e.getID() == id)
                .findFirst()
                .orElse(null);
    }

}
