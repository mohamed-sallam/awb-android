package io.github.mohamed.sallam.awb.database;

import androidx.room.Dao;

import java.util.UUID;

@Dao
public interface DAO_Device {

    /**
     * Generates new UUID for a device the user has added.
     * UUID is a unique id to identify devices.
     */
    public void generateUuid() {} //DAO ?

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

    }

    /**
     * Adds a group of detoxing softwares/websites for a particular device.
     *
     * @param group the group of apps to be added.
     *
     * @author Abdalrhman Hemida.
     */
    public void addGroup(Group group) {

    }

}
