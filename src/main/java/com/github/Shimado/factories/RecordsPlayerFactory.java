package com.github.Shimado.factories;

import com.github.Shimado.RecordsPlayerAPI;
import org.jetbrains.annotations.NotNull;

public interface RecordsPlayerFactory {

    @NotNull
    RecordsPlayerAPI create();

}
