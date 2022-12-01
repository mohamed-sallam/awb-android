package io.github.mohamed.sallam.awb.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.github.mohamed.sallam.awb.Group;

/**
 * Group Data Access Object
 * @author Mohamed Sherif
 */
@Dao
public interface GroupDao {

    @Insert
    void insert(Group... group);

    @Update
    void update(Group group);

    @Delete
    void delete(Group... group);

    @Query("SELECT * FROM Group")
    LiveData<List<Group>> getAll();

    @Query("SELECT * FROM GroupWithBlockedApps")
    LiveData<List<GroupWithBlockedApps>> getAllWithBlockedApps();

}
