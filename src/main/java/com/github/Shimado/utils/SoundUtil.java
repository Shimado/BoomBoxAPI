package com.github.Shimado.utils;

import org.bukkit.Sound;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class SoundUtil {

    private static List<Sound> SOUNDS = (List) Arrays.stream(Sound.class.getDeclaredFields()).map((it) -> {
        try {
            if (!Modifier.isPublic(it.getModifiers())) {
                return null;
            } else {
                Object obj = it.get((Object)null);
                return obj != null && obj instanceof Sound ? (Sound)it.get((Object)null) : null;
            }
        } catch (IllegalAccessException var2) {
            return null;
        }
    }).filter((it) -> {
        return it != null;
    }).collect(Collectors.toList());

    public SoundUtil() {
    }

    public static Sound getSound(String... sounds) {
        Iterator var1 = SOUNDS.iterator();

        while(var1.hasNext()) {
            Object sound = var1.next();
            String[] var3 = sounds;
            int var4 = sounds.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                String s = var3[var5];
                if (sound.toString().equals(s)) {
                    return (Sound)sound;
                }
            }
        }

        return Sound.UI_TOAST_IN;
    }

}
