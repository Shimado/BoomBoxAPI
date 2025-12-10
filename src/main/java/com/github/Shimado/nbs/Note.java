package com.github.Shimado.nbs;

import org.bukkit.Sound;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a musical note with timing and audio properties.
 * This class defines a single note in a musical sequence, including when it should play,
 * what sound it should make, and its audio characteristics.
 *
 * Used for creating scheduled musical sequences or sound effects with precise timing control.
 */

public class Note {

    private int tick;
    private Sound sound;
    private float pitch;
    private float volume;

    public Note(int tick, @NotNull Sound sound, float pitch, float volume) {
        this.tick = tick;
        this.sound = sound;
        this.pitch = pitch;
        this.volume = volume;
    }


    /**
     * Gets the game tick when this note should be played.
     * The tick represents the timing position in a sequence, where each tick
     * typically advances at a regular interval in the game loop.
     *
     * @return the tick value representing when to play this note
     */

    public int getTick(){
        return tick;
    }

    /**
     * Gets the sound that this note will play.
     *
     * @return the Sound associated with this note, never null
     */

    @NotNull
    public Sound getSound(){
        return sound;
    }

    /**
     * Gets the pitch of this note.
     *
     * @return the pitch value of this note (typically between 0.0f and 1.0f)
     */

    public float getPitch(){
        return pitch;
    }

    /**
     * Gets the volume of this note.
     * The volume controls how loud the sound plays
     *
     * @return the volume value of this note  (typically between 0.0f and 1.0f)
     */

    public float getVolume(){
        return volume;
    }

    /**
     * Sets the volume of this note.
     * This allows dynamic adjustment of the note's loudness during playback sequencing.
     *
     * @param volume the new volume value (typically between 0.0f and 1.0f)
     */

    public void setVolume(float volume){
        this.volume = volume;
    }

}
