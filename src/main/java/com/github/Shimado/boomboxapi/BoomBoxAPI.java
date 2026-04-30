package com.github.Shimado.boomboxapi;

import com.github.Shimado.boomboxapi.factories.RecordsPlayerFactory;
import com.github.Shimado.boomboxapi.factories.SongsPlayerFactory;
import com.github.Shimado.boomboxapi.utils.NbsConverter;
import org.jetbrains.annotations.NotNull;

public class BoomBoxAPI {

    private static NbsConverter nbsConverter;
    private static SongsPlayerFactory songsPlayerAPI;
    private static RecordsPlayerFactory recordsPlayerFactory;


    public static void setNbsConverter(@NotNull NbsConverter f) {
        nbsConverter = f;
    }

    @NotNull
    public static NbsConverter getNbsConverter() {
        return nbsConverter;
    }


    public static void setSongsPlayerAPI(@NotNull SongsPlayerFactory f) {
        songsPlayerAPI = f;
    }

    @NotNull
    public static SongsPlayerFactory getSongsPlayerAPI() {
        return songsPlayerAPI;
    }


    public static void setRecordsFactory(@NotNull RecordsPlayerFactory f) {
        recordsPlayerFactory = f;
    }

    @NotNull
    public static RecordsPlayerFactory getRecordsFactory() {
        return recordsPlayerFactory;
    }

}
