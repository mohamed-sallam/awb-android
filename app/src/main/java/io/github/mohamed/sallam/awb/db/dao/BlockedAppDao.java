package io.github.mohamed.sallam.awb.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;

import java.util.List;
import java.util.UUID;

import io.github.mohamed.sallam.awb.db.converter.UuidConverter;
import io.github.mohamed.sallam.awb.db.entity.BlockedApp;

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

    @Query("DELETE FROM blocked_apps_table " +
           "WHERE groupUuid=:groupUuid AND packageName=:packageName")
    void delete(UUID groupUuid, String packageName);

    @Query("DELETE FROM blocked_apps_table WHERE groupUuid = :groupUuid")
    void deleteByGroupUuid(UUID groupUuid);

    @Query("SELECT * FROM blocked_apps_table WHERE groupUuid = :groupUuid")
    LiveData<List<BlockedApp>> getAllByGroupUuid(UUID groupUuid);

    @Query("INSERT INTO blocked_apps_table (path, groupUuid) " +
           "SELECT path, :destinationGroupUuid " +
           "FROM blocked_apps_table " +
           "WHERE groupUuid = :sourceGroupUuid")
    void clone(UUID sourceGroupUuid, UUID destinationGroupUuid);


}
