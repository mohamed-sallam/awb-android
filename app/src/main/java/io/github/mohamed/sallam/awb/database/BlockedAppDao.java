package io.github.mohamed.sallam.awb.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

/**
 * Blocked Apps Data Access Object
 * @author Mohamed Sherif
 */
@Dao
public interface BlockedAppDao {

    @Insert
    void insert(BlockedApp... blockedApps);

    @Delete
    void delete(BlockedApp... blockedApp);

    @Query("SELECT * FROM BlockedApp")
    LiveData<List<BlockedApp>> getAll();

}
