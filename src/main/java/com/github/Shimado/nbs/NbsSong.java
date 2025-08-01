package com.github.Shimado.nbs;

import java.util.HashMap;
import java.util.List;

public class NbsSong {

    private short length;
    private long speed;
    private HashMap<Integer, List<Note>> notes = new HashMap<>();

    public NbsSong(short length, long speed, HashMap<Integer, List<Note>> notes){
        this.length = length;
        this.speed = speed;
        this.notes = notes;
    }


    public short getLength() {
        return length;
    }

    public long getSpeed(){
        return speed;
    }

    public HashMap<Integer, List<Note>> getNotes(){
        return notes;
    }

}
