package io.github.mohamed.sallam.awb.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
import java.util.UUID;

/**
 * Device Data Access Object
 * @author Mohamed Sherif
 */
@Dao
public interface DeviceDAO {

    @Insert
    void insertDevice(Device... device);

    @Update
    void updateDevice(Device device);

    @Delete
    void deleteDevice(Device... device);

    @Query("UPDATE Device SET uuid=:uuid")
    void setUuid(UUID uuid);

    @Query("SELECT * FROM Device")
    LiveData<List<Device>> getAll();

    @Query("SELECT * FROM DeviceWithGroup")
    LiveData<List<DeviceWithGroup>> getAllByGroup();

}
