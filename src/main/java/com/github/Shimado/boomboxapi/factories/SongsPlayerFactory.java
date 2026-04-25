package com.github.Shimado.boomboxapi.factories;

import com.github.Shimado.boomboxapi.api.SongsPlayerAPI;
import org.jetbrains.annotations.NotNull;

public interface SongsPlayerFactory {

    @NotNull
    SongsPlayerAPI create();

}
