package io.github.mohamed.sallam.awb.db.converter;

import androidx.room.TypeConverter;

import java.util.UUID;

/**
 * UUID converter class to use it with Room Data classes.
 *
 * @author Mohamed Sallam
 */
public class UuidConverter {
    @TypeConverter
    public static String fromUuid(UUID uuid) {
        return uuid.toString();
    }

    @TypeConverter
    public static UUID uuidFromString(String string) {
        return UUID.fromString(string);
    }
}