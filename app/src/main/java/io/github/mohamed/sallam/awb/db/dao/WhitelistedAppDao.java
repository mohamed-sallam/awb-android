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

    /**
     * a function that deletes a selected app from the whitelisted apps table.
     *
     * @param groupUuid represent the uuid of the group which contains
     * the selected app to be deleted.
     *
     * @param packageName represent the name of the app to be deleted.
     */
    @Query("DELETE FROM whitelisted_apps_table " +
           "WHERE groupUuid=:groupUuid AND packageName=:packageName")
    void delete(UUID groupUuid, String packageName);

    /**
     * a function that deletes a selected group from the whitelisted apps table.
     *
     * @param groupUuid represent the uuid of the group to be removed.
     */
    @Query("DELETE FROM whitelisted_apps_table WHERE groupUuid = :groupUuid")
    void deleteByGroupUuid(UUID groupUuid);

    /**
     * a function that gets all the whitelisted app in certain group from
     * the whitelisted apps table.
     *
     * @param groupUuid represent the uuid of certain group of apps.
     *
     * @return a live data of whitelisted apps in a list.
     */
    @Query("SELECT * FROM whitelisted_apps_table WHERE groupUuid = :groupUuid")
    LiveData<List<WhitelistedApp>> getAllByGroupUuid(UUID groupUuid);

    /**
     * gives the newly created group a copy of the universal unique identifier of
     * the original group.
     *
     * @param sourceGroupUuid represent the universal unique identifier of the original group.
     *
     * @param destinationGroupUuid represent the universal unique identifier of the copied group
     * which is a random one created initially with group.
     */
    @Query("INSERT INTO whitelisted_apps_table (packageName, groupUuid) " +
           "SELECT packageName, :destinationGroupUuid " +
           "FROM whitelisted_apps_table " +
           "WHERE groupUuid = :sourceGroupUuid")
    void clone(UUID sourceGroupUuid, UUID destinationGroupUuid);


}
