package com.github.Shimado.utils;

import com.github.Shimado.nbs.NbsSong;
import com.github.Shimado.nbs.Note;
import org.bukkit.Sound;

import javax.annotation.Nonnull;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A utility class for parsing NBS (Note Block Studio) files and converting them
 * into playable {@link NbsSong} objects within Minecraft.
 *
 * This converter supports multiple versions of the NBS file format and handles
 * cross-version compatibility for both file structure and sound mappings.
 *
 * <p><b>NBS Format Support:</b>
 * <ul>
 *   <li>Version 0-1: Legacy format</li>
 *   <li>Version 2: Added layer volume</li>
 *   <li>Version 3: Added custom instruments and improved structure</li>
 *   <li>Version 4: Added advanced features like looping and pitch bending</li>
 * </ul>
 * </p>
 */

public class NbsConverter {

    // byte, boolean = 1
    // short, char = 2
    // int, float = 4
    // long, double = 8
    // Format: https://noteblock.studio/nbs

    /**
     * Parses an NBS file and converts it into an {@link NbsSong} object.
     * This method handles the complete NBS file format parsing including metadata,
     * note data, layer information, and version-specific features.
     *
     * <p><b>Parsing Process:</b>
     * <ol>
     *   <li>Reads file header and version information</li>
     *   <li>Extracts song metadata (title, author, description, etc.)</li>
     *   <li>Processes note data with delta encoding</li>
     *   <li>Handles layer volumes and instrument mappings</li>
     *   <li>Converts NBS instruments to Minecraft sounds</li>
     *   <li>Calculates proper pitch values from note numbers</li>
     * </ol>
     * </p>
     *
     * <p><b>Note Conversion:</b>
     * NBS note numbers (0-87) are converted to Minecraft pitch values using the formula:
     * <code>pitch = 2^((note - 33 - 12) / 12)</code>
     * Notes are clamped to the range 0-24 for safety.</p>
     *
     * @param file the NBS file to parse, cannot be null
     * @param isLegacy whether to use legacy instrument mappings (pre-1.12)
     * @return a fully populated {@link NbsSong} object representing the parsed file
     * @throws RuntimeException if there's any error during file parsing, with the original
     *                         exception wrapped as the cause
     */

    public static NbsSong parse(@Nonnull File file, boolean isLegacy) {
        try (DataInputStream inputStream = new DataInputStream(new FileInputStream(file))){

            short length = getShort(inputStream);
            int firstcustominstrument = 10;
            int firstcustominstrumentdiff;
            int version = 0;
            if (length == 0) {
                version = inputStream.readByte();
                firstcustominstrument = inputStream.readByte();
                if (version >= 3) {
                    length = getShort(inputStream);
                }
            }
            firstcustominstrumentdiff = 16 - firstcustominstrument;
            short songHeight = getShort(inputStream);                                      // Число слоев
            String title = getString(inputStream);                                         // Название
            String author = getString(inputStream);                                        // Автор
            getString(inputStream);                                                        // Оригинальный автор
            String description = getString(inputStream);                                   // Описание
            long speed = (long) Math.round(20.0 / (getShort(inputStream) / 100f));         // Скорость цикла
            if(speed <= 0) speed = 1;
            inputStream.readBoolean();                                                     // Auto-saving
            inputStream.readByte();                                                        // Auto-saving duration
            inputStream.readByte();                                                        // Time signature
            getInt(inputStream);                                                           // Minutes spent
            getInt(inputStream);                                                           // Left-clicks
            getInt(inputStream);                                                           // Right-clicks
            getInt(inputStream);                                                           // Note blocks added
            getInt(inputStream);                                                           // Note blocks removed
            getString(inputStream);                                                        // MIDI/Schematic file name
            if (version >= 4) {
                inputStream.readByte();                                                    // Loop on/off
                inputStream.readByte();                                                    // Max loop count
                getShort(inputStream);                                                     // Loop start tick
            }

            HashMap<Integer, List<Note>> notes = new HashMap<>();

            // Чтение нот
            short tick = -1;
            while (true) {
                short jumpTicks = getShort(inputStream);
                if (jumpTicks == 0) break;
                tick += jumpTicks;

                int layer = -1;
                while (true) {
                    short jumpLayers = getShort(inputStream);
                    if (jumpLayers == 0) break;
                    layer += jumpLayers;

                    byte instrument = inputStream.readByte();
                    byte note = inputStream.readByte();

                    if(firstcustominstrumentdiff > 0 && instrument >= firstcustominstrument){
                        instrument += firstcustominstrumentdiff;
                    }

                    if (version >= 4) {
                        byte velocity = inputStream.readByte();
                        byte panning = inputStream.readByte();
                        short pitch = getShort(inputStream);
                    }

                    List<Note> notesList = notes.computeIfAbsent(layer, k -> new ArrayList<>());
                    int noteInt = note - 33;
                    if(noteInt < 0) noteInt = 0;
                    else if(noteInt > 24) noteInt = 24;

                    float pitch = (float) Math.pow(2.0, (double) ((float)(noteInt - 12) / 12.0F));

                    notesList.add(new Note(tick, getSoundFromInstrument(instrument, isLegacy), pitch, 1));
                }
            }

            if (version > 0 && version < 3) {
                length = tick;
            }

            for (int i = 0; i < songHeight; i++) {
                List<Note> notesList = notes.get(i);

                String name = getString(inputStream);
                if(version >= 4){
                    inputStream.readByte();
                }

                byte volume = inputStream.readByte();
                if(version >= 2){
                    inputStream.readByte();
                }

                if(notesList != null){
                    notesList.forEach(it -> it.setVolume(volume / 100.0f));
                    notes.put(i, notesList);
                }
            }

            return new NbsSong(length, speed, notes);
        } catch (Exception e){
            throw new RuntimeException("Error parsing .nbs file", e);
        }
    }

    /**
     * Reads a little-endian short (2 bytes) from the input stream.
     *
     * @param inputStream the data input stream to read from
     * @return the short value read from the stream
     * @throws Exception if an I/O error occurs
     */

    private static short getShort(@Nonnull DataInputStream inputStream) throws Exception {
        int byte1 = inputStream.readUnsignedByte();
        int byte2 = inputStream.readUnsignedByte();
        return (short) (byte1 + (byte2 << 8));
    }

    /**
     * Reads a little-endian integer (4 bytes) from the input stream.
     *
     * @param inputStream the data input stream to read from
     * @return the integer value read from the stream
     * @throws Exception if an I/O error occurs
     */

    private static int getInt(@Nonnull DataInputStream inputStream) throws Exception {
        int byte1 = inputStream.readUnsignedByte();
        int byte2 = inputStream.readUnsignedByte();
        int byte3 = inputStream.readUnsignedByte();
        int byte4 = inputStream.readUnsignedByte();
        return (byte1 + (byte2 << 8) + (byte3 << 16) + (byte4 << 24));
    }

    /**
     * Reads a length-prefixed string from the input stream.
     * The string is prefixed with a 4-byte integer indicating the string length.
     * Carriage return characters (0x0D) are converted to spaces for compatibility.
     *
     * @param inputStream the data input stream to read from
     * @return the string read from the stream
     * @throws Exception if an I/O error occurs
     */

    private static String getString(@Nonnull DataInputStream inputStream) throws Exception {
        int length = getInt(inputStream);
        StringBuilder builder = new StringBuilder(length);
        for (; length > 0; --length) {
            char c = (char) inputStream.readByte();
            if (c == (char) 0x0D) {
                c = ' ';
            }
            builder.append(c);
        }
        return builder.toString();
    }

    /**
     * Maps NBS instrument numbers to Minecraft sound effects.
     * Supports legacy and modern instrument mappings.
     *
     * <p><b>Instrument Mappings:</b>
     * <ul>
     *   <li>0: Harp (Piano)</li>
     *   <li>1: Bass (Double Bass)</li>
     *   <li>2: Basedrum (Bass Drum)</li>
     *   <li>3: Snare (Snare Drum)</li>
     *   <li>4: Hat (Click)</li>
     *   <li>5: Guitar (Acoustic Guitar)</li>
     *   <li>6: Flute (Flute)</li>
     *   <li>7: Bell (Bell)</li>
     *   <li>8: Chime (Chime)</li>
     *   <li>9: Xylophone (Xylophone)</li>
     *   <li>10-15: Modern instruments (1.12+) if not in legacy mode</li>
     * </ul>
     * </p>
     *
     * @param instrument the NBS instrument byte (0-15)
     * @param isLegacy whether to use legacy sound mappings (ignores instruments 10-15)
     * @return the corresponding Minecraft Sound
     */

    private static Sound getSoundFromInstrument(byte instrument, boolean isLegacy) {
        switch (instrument) {
            case 0:  return SoundUtil.getSound("BLOCK.NOTE_BLOCK.HARP", "BLOCK_NOTE_BLOCK_HARP", "BLOCK_NOTE_HARP");
            case 1:  return SoundUtil.getSound("BLOCK.NOTE_BLOCK.BASS", "BLOCK_NOTE_BLOCK_BASS", "BLOCK_NOTE_BASS");
            case 2:  return SoundUtil.getSound("BLOCK.NOTE_BLOCK.BASEDRUM", "BLOCK_NOTE_BLOCK_BASEDRUM", "BLOCK_NOTE_BASEDRUM");
            case 3:  return SoundUtil.getSound("BLOCK.NOTE_BLOCK.SNARE", "BLOCK_NOTE_BLOCK_SNARE", "BLOCK_NOTE_SNARE");
            case 4:  return SoundUtil.getSound("BLOCK.NOTE_BLOCK.HAT", "BLOCK_NOTE_BLOCK_HAT", "BLOCK_NOTE_HAT");
            case 5:  return SoundUtil.getSound("BLOCK.NOTE_BLOCK.GUITAR", "BLOCK_NOTE_BLOCK_GUITAR", "BLOCK_NOTE_GUITAR");
            case 6:  return SoundUtil.getSound("BLOCK.NOTE_BLOCK.FLUTE", "BLOCK_NOTE_BLOCK_FLUTE", "BLOCK_NOTE_FLUTE");
            case 7:  return SoundUtil.getSound("BLOCK.NOTE_BLOCK.BELL", "BLOCK_NOTE_BLOCK_BELL", "BLOCK_NOTE_BELL");
            case 8:  return SoundUtil.getSound("BLOCK.NOTE_BLOCK.CHIME", "BLOCK_NOTE_BLOCK_CHIME", "BLOCK_NOTE_CHIME");
            case 9:  return SoundUtil.getSound("BLOCK.NOTE_BLOCK.XYLOPHONE", "BLOCK_NOTE_BLOCK_XYLOPHONE", "BLOCK_NOTE_XYLOPHONE");
            default:{
                if(!isLegacy){
                    switch (instrument){
                        case 10: return Sound.BLOCK_NOTE_BLOCK_IRON_XYLOPHONE;
                        case 11: return Sound.BLOCK_NOTE_BLOCK_COW_BELL;
                        case 12: return Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO;
                        case 13: return Sound.BLOCK_NOTE_BLOCK_BIT;
                        case 14: return Sound.BLOCK_NOTE_BLOCK_BANJO;
                        case 15: return Sound.BLOCK_NOTE_BLOCK_PLING;
                    }
                }
                return SoundUtil.getSound("BLOCK.NOTE_BLOCK.HARP", "BLOCK_NOTE_BLOCK_HARP", "BLOCK_NOTE_HARP");
            }
        }
    }

}
