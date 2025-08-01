package com.github.Shimado.nbs;

import org.bukkit.Sound;

public class Note {

    private int tick;
    private Sound sound;
    private float pitch;
    private float volume;

    public Note(int tick, Sound sound, float pitch, float volume) {
        this.tick = tick;
        this.sound = sound;
        this.pitch = pitch;
        this.volume = volume;
    }


    public int getTick(){
        return tick;
    }


    public Sound getSound(){
        return sound;
    }


    public float getPitch(){
        return pitch;
    }


    public float getVolume(){
        return volume;
    }

    public void setVolume(float volume){
        this.volume = volume;
    }

}
