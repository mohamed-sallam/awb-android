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
public interface BlockedAppsDAO {

    @Insert
    void insertBlockedApps(BlockedApps... blockedApps);

    @Delete
    void deleteBlockedApps(BlockedApps... blockedApps);

    @Query("SELECT * FROM BlockedApps")
    LiveData<List<BlockedApps>> getAll();

}
