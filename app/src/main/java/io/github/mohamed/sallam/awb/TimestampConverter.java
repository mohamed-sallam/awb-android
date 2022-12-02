package io.github.mohamed.sallam.awb;

import androidx.room.TypeConverter;

import java.sql.Timestamp;

/**
 * Converter Class to convert time from Timestamp to milliseconds (Long).
 *
 * @author Mohamed Sherif
 */
public class TimestampConverter {
    @TypeConverter
    public static Timestamp toTimestamp(Long milliSeconds){
        return (milliSeconds == null) ? null: new Timestamp(milliSeconds);
    }

    @TypeConverter
    public static Long fromTimestamp(Timestamp timestamp){
        return (timestamp == null) ? null: timestamp.getTime();
    }
}