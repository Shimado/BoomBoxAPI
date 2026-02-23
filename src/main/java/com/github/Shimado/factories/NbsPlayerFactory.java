package com.github.Shimado.factories;

import com.github.Shimado.NbsPlayerAPI;
import org.jetbrains.annotations.NotNull;

public interface NbsPlayerFactory {

    @NotNull
    NbsPlayerAPI create();

}
