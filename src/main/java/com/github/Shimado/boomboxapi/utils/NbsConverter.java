package com.github.Shimado.boomboxapi.utils;

import com.github.Shimado.boomboxapi.instances.NbsSong;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public interface NbsConverter {

    // byte, boolean = 1
    // short, char = 2
    // int, float = 4
    // long, double = 8
    // Format: https://noteblock.studio/nbs

    /**
     * Parses an NBS file and converts it into an {@link NbsSong} object.
     * This method handles the complete NBS file format parsing including metadata,
     * note data, layer information, and version-specific features.
     *
     * <p><b>Parsing Process:</b>
     * <ol>
     *   <li>Reads file header and version information</li>
     *   <li>Extracts song metadata (title, author, description, etc.)</li>
     *   <li>Processes note data with delta encoding</li>
     *   <li>Handles layer volumes and instrument mappings</li>
     *   <li>Converts NBS instruments to Minecraft sounds</li>
     *   <li>Calculates proper pitch values from note numbers</li>
     * </ol>
     * </p>
     *
     * <p><b>Note Conversion:</b>
     * NBS note numbers (0-87) are converted to Minecraft pitch values using the formula:
     * <code>pitch = 2^((note - 33 - 12) / 12)</code>
     * Notes are clamped to the range 0-24 for safety.</p>
     *
     * @param file the NBS file to parse, cannot be null
     * @param isLegacy whether to use legacy instrument mappings (pre-1.12)
     * @return a fully populated {@link NbsSong} object representing the parsed file
     * @throws RuntimeException if there's any error during file parsing, with the original
     *                         exception wrapped as the cause
     */

    NbsSong parse(@NotNull File file, boolean isLegacy);


}
