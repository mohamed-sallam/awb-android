package io.github.mohamed.sallam.awb.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;

import java.util.List;
import java.util.UUID;

import io.github.mohamed.sallam.awb.db.converter.UuidConverter;
import io.github.mohamed.sallam.awb.db.entity.WhitelistedApp;

/**
 * Whitelisted Apps Data Access Object.
 *
 * @author Mohamed Sherif
 */
@TypeConverters({UuidConverter.class})
@Dao
public interface WhitelistedAppDao {
    @Insert
    void insert(WhitelistedApp whitelistedApp);

    @Query("DELETE FROM whitelisted_apps_table " +
           "WHERE groupUuid=:groupUuid AND packageName=:packageName")
    void delete(UUID groupUuid, String packageName);

    @Query("DELETE FROM whitelisted_apps_table WHERE groupUuid = :groupUuid")
    void deleteByGroupUuid(UUID groupUuid);

    @Query("SELECT * FROM whitelisted_apps_table WHERE groupUuid = :groupUuid")
    LiveData<List<WhitelistedApp>> getAllByGroupUuid(UUID groupUuid);

    @Query("INSERT INTO whitelisted_apps_table (packageName, groupUuid) " +
           "SELECT packageName, :destinationGroupUuid " +
           "FROM whitelisted_apps_table " +
           "WHERE groupUuid = :sourceGroupUuid")
    void clone(UUID sourceGroupUuid, UUID destinationGroupUuid);


}
