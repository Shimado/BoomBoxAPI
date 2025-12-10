package com.github.Shimado.utils;

import org.bukkit.Sound;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * A utility class for working with Minecraft sounds, providing sound resolution
 * with cross-version compatibility.
 *
 * This class automatically loads all available Sound enum values at class initialization
 * and provides methods to find sounds by multiple potential names, making it useful
 * for handling different sound naming conventions across Minecraft versions.
 */

public class SoundUtil {

    /**
     * A cached list of all available Sound enum values.
     * This list is populated once at class initialization using reflection to access
     * all public static fields of the Sound enum.
     */

    private static final List<Sound> SOUNDS = Arrays.stream(Sound.class.getDeclaredFields())
            .filter(field -> Modifier.isPublic(field.getModifiers()))
            .map(field -> {
                try {
                    return (Sound) field.get(null);
                } catch (IllegalAccessException e) {
                    return null;
                }
            })
            .filter(Objects::nonNull)
            .collect(Collectors.toList());

    public SoundUtil() {
    }

    /**
     * Attempts to find a valid Sound by trying multiple possible sound names in order.
     * This method provides version compatibility by allowing multiple potential sound names
     * and returning the first match found in the available sounds registry.
     *
     * <p><b>Search Algorithm:</b>
     * <ol>
     *   <li>Iterates through all registered Sound enum values</li>
     *   <li>For each sound, compares its string representation against all provided names</li>
     *   <li>Returns the first sound that matches any of the provided names</li>
     *   <li>If no match is found, returns {@link Sound#UI_TOAST_IN} as a safe fallback</li>
     * </ol>
     * </p>
     *
     * <p><b>Usage Example:</b>
     * <pre>
     * // Try multiple possible names for a sound across different versions
     * Sound sound = SoundUtil.getSound("NOTE_BLOCK_BASS", "BLOCK_NOTE_BASS", "NOTE_BASS");
     * </pre>
     * </p>
     *
     * <p><b>Note:</b> The comparison is case-sensitive and uses the exact string representation
     * of the Sound enum value, which is typically the enum constant name.</p>
     *
     * @param sounds one or more potential sound names to search for, in order of preference.
     *               The method will return the first sound that matches any of these names.
     *               Cannot be null.
     * @return the first matching Sound found, or {@link Sound#UI_TOAST_IN} if no matches are found.
     *         Never returns null.
     * @throws NullPointerException if the sounds array is null
     */

    @NotNull
    public static Sound getSound(@NotNull String... sounds) {
        Iterator var1 = SOUNDS.iterator();

        while(var1.hasNext()) {
            Object sound = var1.next();
            String[] var3 = sounds;
            int var4 = sounds.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                String s = var3[var5];
                if (sound.toString().equals(s) || (sound instanceof Sound && ((Sound) sound).getKey().getKey().equalsIgnoreCase(s))) {
                    return (Sound)sound;
                }
            }
        }

        return Sound.UI_TOAST_IN;
    }

}
