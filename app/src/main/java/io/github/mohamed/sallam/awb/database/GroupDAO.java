package io.github.mohamed.sallam.awb.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * Group Data Access Object
 * @author Mohamed Sherif
 */
@Dao
public interface GroupDAO {

    @Insert
    void insertGroup(Group... group);

    @Update
    void updateGroup(Group group);

    @Delete
    void deleteGroup(Group... group);

    @Query("SELECT * FROM Group")
    LiveData<List<Group>> getAll();

    @Query("SELECT * FROM GroupWithBlockedApps")
    LiveData<List<GroupWithBlockedApps>> getAllByBlockedApps();

}
