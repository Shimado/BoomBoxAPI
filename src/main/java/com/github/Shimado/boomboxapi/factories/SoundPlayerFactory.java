package com.github.Shimado.boomboxapi.factories;

import com.github.Shimado.boomboxapi.SoundPlayerAPI;
import org.jetbrains.annotations.NotNull;

public interface SoundPlayerFactory {

    @NotNull
    SoundPlayerAPI create();

}
