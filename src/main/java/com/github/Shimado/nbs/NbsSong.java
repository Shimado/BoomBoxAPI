package com.github.Shimado.nbs;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;

/**
 * Represents a complete song loaded from an NBS (Note Block Studio) file format.
 * This class encapsulates all the musical data required to play a song, including
 * its structure, timing, and all musical notes organized by tick position.
 */

public class NbsSong {

    private short length;
    private long speed;
    private HashMap<Integer, List<Note>> notes = new HashMap<>();

    public NbsSong(short length, long speed, @NotNull HashMap<Integer, List<Note>> notes){
        this.length = length;
        this.speed = speed;
        this.notes = notes;
    }


    /**
     * Gets the total length of the song in ticks.
     * This represents the duration of the entire musical piece from start to finish.
     *
     * @return the song length in ticks
     */

    public short getLength() {
        return length;
    }

    /**
     * Gets the speed (tempo) of the song.
     * The speed determines how quickly the song plays, representing
     * the number of ticks that occur per second.
     *
     * @return the song speed in ticks per second
     */

    public long getSpeed(){
        return speed;
    }

    /**
     * Gets all the notes in the song organized by tick position.
     * The returned map structure allows efficient access to all notes that should
     * be played at any given tick during song playback.
     *
     * <p>The map structure is:
     * <ul>
     *   <li>Key: Integer tick position (when notes should play)</li>
     *   <li>Value: List of Note objects to play at that tick</li>
     * </ul>
     * </p>
     *
     * @return a map of tick positions to lists of notes, never null
     */

    @NotNull
    public HashMap<Integer, List<Note>> getNotes(){
        return notes;
    }

}
