package io.github.mohamed.sallam.awb.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.TypeConverters;
import androidx.room.Update;

import java.util.List;
import java.util.UUID;

import io.github.mohamed.sallam.awb.db.converter.UuidConverter;
import io.github.mohamed.sallam.awb.db.entity.Device;
import io.github.mohamed.sallam.awb.db.relationship.DeviceWithGroups;

/**
 * Device Data Access Object.
 *
 * @author Mohamed Sherif
 */
@TypeConverters({UuidConverter.class})
@Dao
public interface DeviceDao {
    @Insert
    void insert(Device device);

    @Update
    void update(Device device);

    /**
     * Gets the uuid of a device to remove it from the devices table.
     *
     * @param deviceUuid represents the uuid of a device to be removed.
     */
    @Query("DELETE FROM devices_table WHERE uuid=:deviceUuid")
    void delete(UUID deviceUuid);

    /**
     * Set a new uuid for a device and update the devices table.
     *
     * @param oldUuid represents the device uuid before update.
     *
     * @param newUuid represents the device uuid after update.
     */
    @Query("UPDATE devices_table SET uuid=:newUuid WHERE uuid=:oldUuid")
    void setUuid(UUID oldUuid, UUID newUuid);

    /**
     * Gets all devices from the devices table.
     *
     * @return a live data of the list of devices from devices table.
     */
    @Query("SELECT * FROM devices_table")
    LiveData<List<Device>> getAll();

    /**
     * Gets all devices with its groups from the devices table.
     *
     * @return a live data of the list of devices with its groups from devices table.
     */
    @Transaction
    @Query("SELECT * FROM devices_table")
    LiveData<List<DeviceWithGroups>> getAllWithGroups();

    @Query("SELECT * FROM devices_table WHERE thisDevice LIMIT 1")
    LiveData<Device> getThisDevice();
}
