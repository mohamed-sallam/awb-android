package io.github.mohamed.sallam.awb.db.converter;

import androidx.room.TypeConverter;

import java.util.UUID;

/**
 * UUID converter class to use it with Room Data classes.
 *
 * @see
 *
 * @author Mohamed Sallam
 */
public class UuidConverter {
    /**
     * @param uuid represent a group/device uuid.
     *
     * @return uuid but in a string format.
     */
    @TypeConverter
    public static String fromUuid(UUID uuid) {
        return uuid.toString();
    }

    /**
     * @param string represent the string format of a group/device uuid.
     *
     * @return group/device uuid in UUID format.
     */
    @TypeConverter
    public static UUID uuidFromString(String string) {
        return UUID.fromString(string);
    }
}