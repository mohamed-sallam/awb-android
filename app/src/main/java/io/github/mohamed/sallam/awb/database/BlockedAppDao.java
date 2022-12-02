package io.github.mohamed.sallam.awb.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;

import java.util.List;
import java.util.UUID;

import io.github.mohamed.sallam.awb.BlockedApp;
import io.github.mohamed.sallam.awb.UuidConverter;

/**
 * Blocked Apps Data Access Object.
 *
 * @author Mohamed Sherif
 */
@TypeConverters({UuidConverter.class})
@Dao
public interface BlockedAppDao {
    @Insert
    void insert(BlockedApp blockedApp);

    @Delete
    void delete(BlockedApp blockedApp);

    @Query("SELECT * FROM blocked_apps_table")
    LiveData<List<BlockedApp>> getAll();

    @Query("INSERT INTO blocked_apps_table (path, groupUuid) " +
           "SELECT path, :destinationGroupUuid " +
           "FROM blocked_apps_table " +
           "WHERE groupUuid = :sourceGroupUuid")
    void clone(UUID sourceGroupUuid, UUID destinationGroupUuid);
}
