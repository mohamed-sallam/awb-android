package io.github.mohamed.sallam.awb;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import static io.github.mohamed.sallam.awb.Device.Os.*;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Class Device has methods to add and delete blocked apps groups for the device
 * and generate a unique id for the device.
 *
 * @author Abdalrhman Hemida
 */
@Entity(tableName = "devices_table")
public class Device {
    // Fields
    private String name;
    private String operatingSystemName;
    private Os operatingSystemType = UNKOWN;
    public static final String AWB_VERSION = "0.1.0v";
    private String ipAddressV4 ;
    @PrimaryKey
    private UUID uuid;
    @Ignore
    @Relation(parentColumn = "" )
    private ArrayList<Group> groups = new ArrayList<Group>();



    /**
     * Enum to optimize the interactions with operating system names by
     * assigning constant names to a group of numeric integer values. It makes
     * constant values more readable.
     *
     * @author Abdalrhman Hemida
     */
    public enum Os {
        UNKOWN(0),
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

    // Constructor
    public Device() {}

    public Device(String name, String operatingSystemName, Os operatingSystemType,
                  String ipAddressV4, UUID uuid, ArrayList<Group> groups) {
        this.name = name;
        this.operatingSystemName = operatingSystemName;
        this.operatingSystemType = operatingSystemType;
        this.ipAddressV4 = ipAddressV4;
        this.uuid = uuid;
        this.groups = groups;
    }

    /**
     * Gets the device name.
     *
     * @return the value of name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the operating system name.
     *
     * @return the value of operating system name.
     */
    public String getOperatingSystemName() {
        return operatingSystemName;
    }

    /**
     * Gets the value of IP Address (V4) of the Device.
     *
     * @return the value of IP Address.
     */
    public String getIpAddressV4() {
        return ipAddressV4;
    }

    /**
     * Gets the value of UUID.
     *
     * @return device UUID.
     */
    public UUID getUuid() {
        return uuid;
    }

    /**
     * Gets a group of blocked apps.
     *
     * @return ArrayList of detoxing apps groups.
     */
    public ArrayList<Group> getGroups() {
        return groups;
    }

    /**
     * Gets the value of operating system type.
     *
     * @return the value of operating system type.
     */
    public Os getOperatingSystemType() {
        return operatingSystemType;
    }


}
