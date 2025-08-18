package com.github.Shimado;

import com.github.Shimado.factories.NbsPlayerFactory;
import com.github.Shimado.factories.RecordsPlayerFactory;
import com.github.Shimado.factories.SoundPlayerFactory;

public class BoomBoxAPI {

    private static NbsPlayerFactory nbsPlayerFactory;
    private static RecordsPlayerFactory recordsPlayerFactory;
    private static SoundPlayerFactory soundPlayerFactory;

    public static void setNbsFactory(NbsPlayerFactory f) {
        nbsPlayerFactory = f;
    }

    public static NbsPlayerFactory getNbsFactory() {
        return nbsPlayerFactory;
    }


    public static void setRecordsFactory(RecordsPlayerFactory f) {
        recordsPlayerFactory = f;
    }

    public static RecordsPlayerFactory getRecordsFactory() {
        return recordsPlayerFactory;
    }


    public static void setSoundFactory(SoundPlayerFactory f) {
        soundPlayerFactory = f;
    }

    public static SoundPlayerFactory getSoundFactory() {
        return soundPlayerFactory;
    }

}
