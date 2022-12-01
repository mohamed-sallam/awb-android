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

    @Query("UPDATE Device SET uuid=:newUuid WHERE uuid=:oldUuid")
    void setUuid(UUID oldUuid, UUID newUuid);

    @Query("SELECT * FROM Device")
    LiveData<List<Device>> getAll();

    @Query("SELECT * FROM DeviceWithGroups")
    LiveData<List<DeviceWithGroups>> getAllWithGroups();

}
