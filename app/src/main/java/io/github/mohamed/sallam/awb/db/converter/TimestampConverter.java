package io.github.mohamed.sallam.awb.db.converter;

import androidx.room.TypeConverter;

import java.sql.Timestamp;

/**
 * Converter Class to convert time from Timestamp to milliseconds (Long) and vice versa.
 *
 * @see
 *
 * @author Mohamed Sherif
 */
public class TimestampConverter {
    /**
     * @param milliSeconds represent time in milliseconds.
     *
     * @return time in Timestamp format.
     */
    @TypeConverter
    public static Timestamp toTimestamp(Long milliSeconds){
        return (milliSeconds == null) ? null: new Timestamp(milliSeconds);
    }

    /**
     * @param timestamp represent time in Timestamp format.
     *
     * @return time in milliseconds format.
     */
    @TypeConverter
    public static Long fromTimestamp(Timestamp timestamp){
        return (timestamp == null) ? null: timestamp.getTime();
    }
}