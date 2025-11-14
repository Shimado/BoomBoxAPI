package com.github.Shimado;

import com.github.Shimado.factories.NbsPlayerFactory;
import com.github.Shimado.factories.RecordsPlayerFactory;
import com.github.Shimado.factories.SoundPlayerFactory;

import javax.annotation.Nonnull;

public class BoomBoxAPI {

    private static NbsPlayerFactory nbsPlayerFactory;
    private static RecordsPlayerFactory recordsPlayerFactory;
    private static SoundPlayerFactory soundPlayerFactory;


    public static void setNbsFactory(@Nonnull NbsPlayerFactory f) {
        nbsPlayerFactory = f;
    }

    @Nonnull
    public static NbsPlayerFactory getNbsFactory() {
        return nbsPlayerFactory;
    }


    public static void setRecordsFactory(@Nonnull RecordsPlayerFactory f) {
        recordsPlayerFactory = f;
    }

    @Nonnull
    public static RecordsPlayerFactory getRecordsFactory() {
        return recordsPlayerFactory;
    }


    public static void setSoundFactory(@Nonnull SoundPlayerFactory f) {
        soundPlayerFactory = f;
    }

    @Nonnull
    public static SoundPlayerFactory getSoundFactory() {
        return soundPlayerFactory;
    }

}
