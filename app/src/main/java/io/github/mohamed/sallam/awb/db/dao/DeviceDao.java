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


    @Query("DELETE FROM devices_table WHERE uuid=:deviceUuid")
    void delete(UUID deviceUuid);

    @Query("UPDATE devices_table SET uuid=:newUuid WHERE uuid=:oldUuid")
    void setUuid(UUID oldUuid, UUID newUuid);

    @Query("SELECT * FROM devices_table")
    LiveData<List<Device>> getAll();

    @Query("SELECT * FROM devices_table WHERE thisDevice LIMIT 1")
    LiveData<Device> getThisDevice();

    @Transaction
    @Query("SELECT * FROM devices_table")
    LiveData<List<DeviceWithGroups>> getAllWithGroups();
}
