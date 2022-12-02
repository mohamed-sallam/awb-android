package io.github.mohamed.sallam.awb.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;
import androidx.room.Update;

import java.util.List;
import java.util.UUID;

import io.github.mohamed.sallam.awb.Device;

/**
 * Device Data Access Object
 * @author Mohamed Sherif
 */
@TypeConverters({UuidConverter.class})
@Dao
public interface DeviceDao {

    @Insert
    void insert(Device... device);

    @Update
    void update(Device device);

    @Delete
    void delete(Device... device);

    @Query("UPDATE devices_table SET uuid=:newUuid WHERE uuid=:oldUuid")
    void setUuid(UUID oldUuid, UUID newUuid);

    @Query("SELECT * FROM devices_table")
    LiveData<List<Device>> getAll();

    @Transaction
    @Query("SELECT * FROM devices_table")
    LiveData<List<DeviceWithGroups>> getAllWithGroups();
}
