package io.github.mohamed.sallam.awb.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.TypeConverters;

import java.util.List;
import java.util.UUID;

import io.github.mohamed.sallam.awb.db.converter.UuidConverter;
import io.github.mohamed.sallam.awb.db.entity.Group;
import io.github.mohamed.sallam.awb.db.relationship.GroupWithWhitelistedApps;

/**
 * Group Data Access Object.
 *
 * @author Mohamed Sherif
 */
@TypeConverters({UuidConverter.class})
@Dao
public interface GroupDao {
    @Insert
    void insert(Group group);

    /**
     * renames a group of apps.
     *
     * @param uuid represent the uuid of a group of apps to be renamed.
     *
     * @param name represent the new name given to the group of apps.
     */
    @Query("UPDATE groups_table SET name = :name WHERE uuid = :uuid")
    void rename(UUID uuid, String name);

    /**
     * removes a selected group of apps from the groups table.
     *
     * @param groupUuid represent the uuid of a group of apps to be deleted.
     */
    @Query("DELETE FROM groups_table WHERE uuid=:groupUuid")
    void delete(UUID groupUuid);

    /**
     * gets a certain group.
     *
     * @param groupUuid represent the uuid of a group of apps to be represented.
     *
     * @return a live data of a selected group.
     */
    @Query("SELECT * FROM groups_table WHERE uuid = :groupUuid ")
    LiveData<Group> get(UUID groupUuid);

    /**
     * gets all groups in a certain device.
     *
     * @param deviceUuid represent the uuid of a device.
     *
     * @return a live data of the groups in a selected device.
     */
    @Query("SELECT * FROM groups_table WHERE uuid = :deviceUuid")
    LiveData<List<Group>> getAllByDevice(UUID deviceUuid);

    /**
     * changes the device in relation with app groups.
     *
     * @param oldDeviceUuid represent the uuid of the old device.
     *
     * @param newDeviceUuid represent the new uuid of the new selected device.
     */
    @Query("UPDATE groups_table "             +
           "SET   deviceUuid=:newDeviceUuid " +
           "WHERE deviceUuid=:oldDeviceUuid"  )
    void replaceDeviceUuid(UUID oldDeviceUuid, UUID newDeviceUuid);

    /**
     * gets a certain group with its whitelisted apps.
     *
     * @param uuid represent the uuid of a group to get all of its whitelisted apps.
     *
     * @return a live data of a group with its whitelisted apps.
     */
    @Transaction
    @Query("SELECT * FROM groups_table WHERE uuid=:uuid")
    LiveData<GroupWithWhitelistedApps> getWithWhitelistedApps(UUID uuid);
}
