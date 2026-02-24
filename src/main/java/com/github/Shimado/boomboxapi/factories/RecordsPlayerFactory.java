package com.github.Shimado.boomboxapi.factories;

import com.github.Shimado.boomboxapi.RecordsPlayerAPI;
import org.jetbrains.annotations.NotNull;

public interface RecordsPlayerFactory {

    @NotNull
    RecordsPlayerAPI create();

}
