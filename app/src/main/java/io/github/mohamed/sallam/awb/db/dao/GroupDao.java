package io.github.mohamed.sallam.awb.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.TypeConverters;
import androidx.room.Update;

import java.util.List;
import java.util.UUID;

import io.github.mohamed.sallam.awb.db.converter.UuidConverter;
import io.github.mohamed.sallam.awb.db.entity.Group;
import io.github.mohamed.sallam.awb.db.relationship.GroupWithBlockedApps;

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

    @Query("UPDATE groups_table SET name = :name WHERE uuid = :uuid")
    void rename(String name, UUID uuid);

    @Delete
    void delete(UUID groupUuid);

    @Query("SELECT * FROM groups_table WHERE uuid = :deviceUuid")
    LiveData<List<Group>> getAll(UUID deviceUuid);

    @Transaction
    @Query("SELECT * FROM groups_table WHERE uuid = :deviceUuid")
    LiveData<List<GroupWithBlockedApps>> getAllWithBlockedApps(UUID deviceUuid);
}
