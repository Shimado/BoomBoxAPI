package com.github.Shimado.boomboxapi.factories;

import com.github.Shimado.boomboxapi.NbsPlayerAPI;
import org.jetbrains.annotations.NotNull;

public interface NbsPlayerFactory {

    @NotNull
    NbsPlayerAPI create();

}
