package com.github.Shimado.boomboxapi.instances;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;

/**
 * Represents a complete song loaded from an NBS (Note Block Studio) file format.
 * This class encapsulates all the musical data required to play a song, including
 * its structure, timing, and all musical notes organized by tick position.
 */

public interface NbsSong {

    /**
     * Gets the total length of the song in ticks.
     * This represents the duration of the entire musical piece from start to finish.
     *
     * @return the song length in ticks
     */

    short getLength();

    /**
     * Gets the speed (tempo) of the song.
     * The speed determines how quickly the song plays, representing
     * the number of ticks that occur per second.
     *
     * @return the song speed in ticks per second
     */

    long getSpeed();

    /**
     * Gets all the notes in the song organized by tick position.
     * The returned map structure allows efficient access to all notes that should
     * be played at any given tick during song playback.
     *
     * <p>The map structure is:</p>
     * <ul>
     *   <li>Key: Integer tick position (when notes should play)</li>
     *   <li>Value: List of Note objects to play at that tick</li>
     * </ul>
     *
     * @return a map of tick positions to lists of notes, never null
     */

    @NotNull
    HashMap<Integer, List<Note>> getNotes();

}
