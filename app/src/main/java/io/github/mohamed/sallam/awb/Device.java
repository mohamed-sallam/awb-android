package io.github.mohamed.sallam.awb;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Class Device has methods to add and delete blocked apps groups for the device
 * and generate a unique id for the device.
 *
 * @author Abdalrhman Hemida
 *
 */
public class Device {
    // Fields
    private String name;
    private String operatingSystemName;
    private undef operatingSystemType = null;
    public static final String AWB_VERSION = "0.1.0v";
    private String ipAddressV4 = "127.0.0.1";
    private UUID uuid;
    private ArrayList<Group> groups = new ArrayList<>();

    // Constructor
    public Device() {}

    // Mutators
    /**
     * Sets the value of device name.
     *
     * @param newVar the new value of device name.
     *
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
     *
     */
    public void setOperatingSystemType(undef newVar) {
        operatingSystemType = newVar;
    }

    /**
     * Sets the value of device's IP Address (V4).
     *
     * @param newVar the new value of ipAddressV4.
     *
     */
    public void setIpAddressV4(String newVar) {
        ipAddressV4 = newVar;
    }

    /**
     * Generates new UUID for a device the user has added.
     * UUID is a unique id to identify devices.
     *
     */
    public void generateUuid() {}


    // Accessors
    /**
     * Gets the device name.
     *
     * @return the value of name.
     *
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the operating system name.
     *
     * @return the value of operating system name.
     *
     */
    public String getOperatingSystemName() {
        return operatingSystemName;
    }

    /**
     * Gets the value of IP Address (V4) of the Device.
     *
     * @return the value of IP Address.
     *
     */
    public String getIpAddressV4() {
        return ipAddressV4;
    }

    /**
     * Gets the value of GUID.
     *
     * @return device UUID.
     *
     */
    public UUID getUuid() {
        return uuid;
    }

    /**
     * Gets a group of blocked apps.
     *
     * @return ArrayList of detoxing apps groups.
     *
     */
    public ArrayList<Group> getGroups() {
        return groups;
    }

    /**
     * Gets the value of operating system type.
     *
     * @return the value of operating system type.
     *
     */
    public Os getOperatingSystemType() {
        return operatingSystemType;
    }

    // Methods
    /**
     * Deletes a group of apps which user has defined from a particular device
     * using the UUID of the group.
     *
     * @param groupGuid the group id.
     *
     * @author Abdalrhman Hemida.
     *
     */
    public void deleteGroup(UUID groupGuid) {
        groups.removeIf(group -> group.getUuid().equals(groupGuid));
    }

    /**
     * Adds a group of detoxing apps for a particular device.
     *
     * @param group the group of apps to be added.
     *
     * @author Abdalrhman Hemida.
     *
     */
    public void addGroup(Group group) {
        groups.add(group);
    }

}
