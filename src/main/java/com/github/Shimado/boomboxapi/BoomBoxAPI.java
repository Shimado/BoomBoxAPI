package com.github.Shimado.boomboxapi;

import com.github.Shimado.boomboxapi.factories.NbsPlayerFactory;
import com.github.Shimado.boomboxapi.factories.RecordsPlayerFactory;
import com.github.Shimado.boomboxapi.factories.SoundPlayerFactory;
import com.github.Shimado.boomboxapi.utils.NbsConverter;
import org.jetbrains.annotations.NotNull;

public class BoomBoxAPI {

    private static NbsConverter nbsConverter;
    private static NbsPlayerFactory nbsPlayerFactory;
    private static RecordsPlayerFactory recordsPlayerFactory;
    private static SoundPlayerFactory soundPlayerFactory;


    public static void setNbsConverter(@NotNull NbsConverter f) {
        nbsConverter = f;
    }

    @NotNull
    public static NbsConverter getNbsConverter() {
        return nbsConverter;
    }


    public static void setNbsFactory(@NotNull NbsPlayerFactory f) {
        nbsPlayerFactory = f;
    }

    @NotNull
    public static NbsPlayerFactory getNbsFactory() {
        return nbsPlayerFactory;
    }


    public static void setRecordsFactory(@NotNull RecordsPlayerFactory f) {
        recordsPlayerFactory = f;
    }

    @NotNull
    public static RecordsPlayerFactory getRecordsFactory() {
        return recordsPlayerFactory;
    }


    public static void setSoundFactory(@NotNull SoundPlayerFactory f) {
        soundPlayerFactory = f;
    }

    @NotNull
    public static SoundPlayerFactory getSoundFactory() {
        return soundPlayerFactory;
    }

}
