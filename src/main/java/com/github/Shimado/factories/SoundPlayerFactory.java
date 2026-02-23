package com.github.Shimado.factories;

import com.github.Shimado.SoundPlayerAPI;
import org.jetbrains.annotations.NotNull;

public interface SoundPlayerFactory {

    @NotNull
    SoundPlayerAPI create();

}
