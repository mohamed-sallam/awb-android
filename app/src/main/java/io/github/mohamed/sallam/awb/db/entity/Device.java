package io.github.mohamed.sallam.awb.db.entity;

import static io.github.mohamed.sallam.awb.db.entity.Device.Os.UNKNOWN;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.UUID;

import io.github.mohamed.sallam.awb.db.converter.UuidConverter;
import io.github.mohamed.sallam.awb.repo.AggregateRoot;

/**
 * Class `Device` to define the structure of a device object. It represents a table
 * on our database contains all devices created on the application by the user
 *
 * @author Abdalrhman Hemida
 * @author Mohamed Sallam
 */
@TypeConverters({UuidConverter.class})
@Entity(tableName = "devices_table")
public class Device implements AggregateRoot {
    // Fields
    @NonNull
    @PrimaryKey
    public UUID uuid = UUID.randomUUID();
    public String name;
    public boolean thisDevice;
    public String operatingSystemName;
    public Os operatingSystemType = UNKNOWN;
    @ColumnInfo(defaultValue = "127.0.0.1")
    public String ipAddressV4;
    public String secretKey;
    public static final String AWB_VERSION = "0.1.0v";

    /**
     * Enum to optimize the interactions with operating system names by
     * assigning constant names to a group of numeric integer values. It makes
     * constant values more readable.
     *
     * @see
     *
     * @author Abdalrhman Hemida
     */
    public enum Os {
        UNKNOWN(0),
        UNIX(1),
        ANDROID(2),
        WINDOWS(3);

        private final int osNum;

        Os(int osNum) {
            this.osNum = osNum;
        }

        @NonNull
        @Override
        public String toString() {
            return Integer.toString(osNum);
        }
    }
}
