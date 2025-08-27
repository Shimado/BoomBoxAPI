package com.github.Shimado.utils;

import com.github.Shimado.nbs.NbsSong;
import com.github.Shimado.nbs.Note;
import org.bukkit.Sound;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NbsConverter {

    // byte, boolean = 1
    // short, char = 2
    // int, float = 4
    // long, double = 8
    // Format: https://noteblock.studio/nbs

    public static NbsSong parse(File file, boolean isLegacy) {
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
            long speed = (long) Math.ceil(20.0 / (getShort(inputStream) / 100f));          // Скорость цикла
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

    private static short getShort(DataInputStream inputStream) throws Exception {
        int byte1 = inputStream.readUnsignedByte();
        int byte2 = inputStream.readUnsignedByte();
        return (short) (byte1 + (byte2 << 8));
    }

    private static int getInt(DataInputStream inputStream) throws Exception {
        int byte1 = inputStream.readUnsignedByte();
        int byte2 = inputStream.readUnsignedByte();
        int byte3 = inputStream.readUnsignedByte();
        int byte4 = inputStream.readUnsignedByte();
        return (byte1 + (byte2 << 8) + (byte3 << 16) + (byte4 << 24));
    }

    private static String getString(DataInputStream inputStream) throws Exception {
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
