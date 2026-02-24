package com.github.Shimado.boomboxapi.factories;

import com.github.Shimado.boomboxapi.utils.NbsConverter;
import org.jetbrains.annotations.NotNull;

public interface NbsConverterFactory {

    @NotNull
    NbsConverter create();

}
