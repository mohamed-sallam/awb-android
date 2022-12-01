package io.github.mohamed.sallam.awb.database;

import androidx.room.TypeConverter;

import java.sql.Timestamp;

/**
 * Converter Class to convert time from milliseconds to human readable date
 * @author Mohamed Sherif
 */

public class TimestampConverter {

    @TypeConverter
    public static Timestamp toTimestamp(Long millisecondes){
        return millisecondes==null?null:new Timestamp(millisecondes);
    }

    @TypeConverter
    public static Long fromTimestamp(Timestamp timestamp){
        return timestamp==null?null:timestamp.getTime();
    }
}
