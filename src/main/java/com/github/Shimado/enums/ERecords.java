package com.github.Shimado.enums;

import com.github.Shimado.utils.SoundUtil;
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

public enum ERecords {

    MUSIC_DISC_13(1, new String[]{"MUSIC_DISC_13", "GOLD_RECORD"}, new String[]{"MUSIC_DISC.13", "MUSIC_DISC_13", "RECORD_13"}, 178),
    MUSIC_DISC_CAT(2, new String[]{"MUSIC_DISC_CAT", "GREEN_RECORD"}, new String[]{"MUSIC_DISC.CAT", "MUSIC_DISC_CAT", "RECORD_CAT"}, 185),
    MUSIC_DISC_BLOCKS(3, new String[]{"MUSIC_DISC_BLOCKS", "RECORD_3"}, new String[]{"MUSIC_DISC.BLOCKS", "MUSIC_DISC_BLOCKS", "RECORD_BLOCKS"}, 345),
    MUSIC_DISC_CHIRP(4, new String[]{"MUSIC_DISC_CHIRP", "RECORD_4"}, new String[]{"MUSIC_DISC.CHIRP", "MUSIC_DISC_CHIRP", "RECORD_CHIRP"},185),
    MUSIC_DISC_FAR(5, new String[]{"MUSIC_DISC_FAR", "RECORD_5"}, new String[]{"MUSIC_DISC.FAR", "MUSIC_DISC_FAR", "RECORD_FAR"}, 174),
    MUSIC_DISC_MALL(6, new String[]{"MUSIC_DISC_MALL", "RECORD_6"}, new String[]{"MUSIC_DISC.MALL", "MUSIC_DISC_MALL", "RECORD_MALL"}, 197),
    MUSIC_DISC_MELLOHI(7, new String[]{"MUSIC_DISC_MELLOHI", "RECORD_7"}, new String[]{"MUSIC_DISC.MELLOHI", "MUSIC_DISC_MELLOHI", "RECORD_MELLOHI"}, 96),
    MUSIC_DISC_STAL(8, new String[]{"MUSIC_DISC_STAL", "RECORD_8"}, new String[]{"MUSIC_DISC.STAL", "MUSIC_DISC_STAL", "RECORD_STAL"}, 150),
    MUSIC_DISC_STRAD(9, new String[]{"MUSIC_DISC_STRAD", "RECORD_9"}, new String[]{"MUSIC_DISC.STRAD", "MUSIC_DISC_STRAD", "RECORD_STRAD"}, 188),
    MUSIC_DISC_WARD(10, new String[]{"MUSIC_DISC_WARD", "RECORD_10"}, new String[]{"MUSIC_DISC.WARD", "MUSIC_DISC_WARD", "RECORD_WARD"}, 251),
    MUSIC_DISC_11(11, new String[]{"MUSIC_DISC_11", "RECORD_11"}, new String[]{"MUSIC_DISC.11", "MUSIC_DISC_11", "RECORD_11"}, 71),
    MUSIC_DISC_WAIT(12, new String[]{"MUSIC_DISC_WAIT", "RECORD_12"}, new String[]{"MUSIC_DISC.WAIT", "MUSIC_DISC_WAIT", "RECORD_WAIT"}, 238),
    MUSIC_DISC_OTHERSIDE(13, new String[]{"MUSIC_DISC_OTHERSIDE", "Y"}, new String[]{"MUSIC_DISC.OTHERSIDE", "MUSIC_DISC_OTHERSIDE", "X"}, 195),
    MUSIC_DISC_RELIC(14, new String[]{"MUSIC_DISC_RELIC", "Y"}, new String[]{"MUSIC_DISC.RELIC", "MUSIC_DISC_RELIC", "X"}, 218),
    MUSIC_DISC_5(15, new String[]{"MUSIC_DISC_5", "Y"}, new String[]{"MUSIC_DISC.5", "MUSIC_DISC_5", "X"}, 178),
    MUSIC_DISC_PIGSTEP(16, new String[]{"MUSIC_DISC_PIGSTEP", "Y"}, new String[]{"MUSIC_DISC.PIGSTEP", "MUSIC_DISC_PIGSTEP", "X"}, 148),
    MUSIC_DISC_PRECIPICE(17, new String[]{"MUSIC_DISC_PRECIPICE", "Y"}, new String[]{"MUSIC_DISC.PRECIPICE", "MUSIC_DISC_PRECIPICE", "X"}, 299),
    MUSIC_DISC_CREATOR(18, new String[]{"MUSIC_DISC_CREATOR", "Y"}, new String[]{"MUSIC_DISC.CREATOR", "MUSIC_DISC_CREATOR", "X"}, 176),
    MUSIC_DISC_CREATOR_MUSIC_BOX(19, new String[]{"MUSIC_DISC_CREATOR_MUSIC_BOX", "Y"}, new String[]{"MUSIC_DISC.CREATOR_MUSIC_BOX", "MUSIC_DISC_CREATOR_MUSIC_BOX", "X"}, 73),
    MUSIC_DISC_TEARS(20, new String[]{"MUSIC_DISC_TEARS", "Y"}, new String[]{"MUSIC_DISC.TEARS", "MUSIC_DISC_TEARS", "X"}, 175),
    MUSIC_DISC_LAVA_CHICKEN(21, new String[]{"MUSIC_DISC_LAVA_CHICKEN", "Y"}, new String[]{"MUSIC_DISC.LAVA_CHICKEN", "MUSIC_DISC_LAVA_CHICKEN", "X"}, 135);

    private int id;
    private String[] materials;
    private String[] sounds;
    private int duration;

    ERecords(int id, @NotNull String[] materials, @NotNull String[] sounds, int duration) {
        this.id = id;
        this.materials = materials;
        this.sounds = sounds;
        this.duration = duration;
    }


    /**
     * Gets the unique identifier for this music disc.
     *
     * @return the music disc ID
     */

    public int getID(){
        return id;
    }

    /**
     * Gets the duration of the music track in seconds.
     *
     * @return the duration in seconds
     */

    public int getDuration(){
        return duration;
    }

    /**
     * Gets the material representation of this music disc.
     * This method attempts to find a valid material by trying each material name
     * in the materials array until a non-null material is found.
     *
     * @return the Material for this music disc, or Material.STONE if no valid material is found
     */

    public Material getMaterial(){
        for(int i = 0; i < materials.length; ++i) {
            String material = materials[i];
            if (Material.getMaterial(material) != null) {
                return Material.getMaterial(material);
            }
        }

        return Material.STONE;
    }

    /**
     * Gets the sound associated with this music disc.
     * Uses SoundUtil to find a valid sound from the sounds array.
     *
     * @return the Sound for this music disc
     */

    public Sound getSound(){
        return SoundUtil.getSound(sounds);
    }

    /**
     * Finds a music disc record by its material.
     *
     * @param material the material to search for, cannot be null
     * @return the matching ERecords instance, or null if no match is found
     */

    @Nullable
    public static ERecords findByMaterial(@NotNull Material material){
        return Arrays.stream(ERecords.values()).filter(e -> e.getMaterial().equals(material)).findFirst().orElse(null);
    }

    /**
     * Finds a music disc record by its enum name.
     *
     * @param name the enum name to search for (case-sensitive), cannot be null
     * @return the matching ERecords instance, or null if no match is found
     */

    @Nullable
    public static ERecords findByName(@NotNull String name){
        return Arrays.stream(ERecords.values()).filter(e -> e.toString().equals(name)).findFirst().orElse(null);
    }

    /**
     * Finds a music disc record by its enum ID.
     *
     * @param id the enum id to search
     * @return the matching ERecords instance, or null if no match is found
     */

    @Nullable
    public static ERecords findByID(int id){
        return Arrays.stream(ERecords.values()).filter(e -> e.getID() == id).findFirst().orElse(null);
    }

}
