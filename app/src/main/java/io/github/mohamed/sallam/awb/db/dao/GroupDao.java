package io.github.mohamed.sallam.awb.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

import io.github.mohamed.sallam.awb.db.entity.Group;
import io.github.mohamed.sallam.awb.db.relationship.GroupWithBlockedApps;

/**
 * Group Data Access Object.
 *
 * @author Mohamed Sherif
 */
@Dao
public interface GroupDao {
    @Insert
    void insert(Group group);

    @Update
    void update(Group group);

    @Delete
    void delete(Group group);

    @Query("SELECT * FROM groups_table")
    LiveData<List<Group>> getAll();

    @Transaction
    @Query("SELECT * FROM groups_table")
    LiveData<List<GroupWithBlockedApps>> getAllWithBlockedApps();
}
