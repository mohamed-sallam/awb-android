package io.github.mohamed.sallam.awb.database;

import androidx.annotation.NonNull;
import static io.github.mohamed.sallam.awb.database.Device.Os.*;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Class Device has methods to add and delete blocked apps groups for the device
 * and generate a unique id for the device.
 *
 * @author Abdalrhman Hemida
 */
public class Device {
    // Fields
    private String name;
    private String operatingSystemName;
    private Os operatingSystemType = UNKOWN;
    public static final String AWB_VERSION = "0.1.0v";
    private String ipAddressV4 = "127.0.0.1";
    private UUID uuid;
    private ArrayList<Group> groups = new ArrayList<>();

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

    // Mutators
    /**
     * Sets the value of device name.
     *
     * @param newVar the new value of device name.
     */
    public void setName(String newVar) {
        name = newVar;
    }

    /**
     * Sets the value of operatingSystemName.
     *
     * @param newVar the new value of operatingSystemName.
     *
     */
    public void setOperatingSystemName(String newVar) {
        operatingSystemName = newVar;
    }

    /**
     * Sets the value of operating system type.
     *
     * @param newVar the new value of operatingSystemType.
     */
    public void setOperatingSystemType(Os newVar) {
        operatingSystemType = newVar;
    }

    /**
     * Sets the value of device's IP Address (V4).
     *
     * @param newVar the new value of ipAddressV4.
     */
    public void setIpAddressV4(String newVar) {
        ipAddressV4 = newVar;
    }

    /**
     * Generates new UUID for a device the user has added.
     * UUID is a unique id to identify devices.
     */
    public void generateUuid() {}

    // Accessors
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

    // Methods
    /**
     * Deletes a group of apps which user has defined from a particular device
     * using the UUID of the group.
     *
     * @param groupUuid the group id.
     *
     * @author Abdalrhman Hemida.
     */
    public void deleteGroup(UUID groupUuid) {
        groups.removeIf(group -> group.getUuid().equals(groupUuid));
    }

    /**
     * Adds a group of detoxing softwares/websites for a particular device.
     *
     * @param group the group of apps to be added.
     *
     * @author Abdalrhman Hemida.
     */
    public void addGroup(Group group) {
        groups.add(group);
    }
}
