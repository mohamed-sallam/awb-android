package io.github.mohamed.sallam.awb.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import io.github.mohamed.sallam.awb.DetoxPeriod;

/**
 * Detox Period Data Access Object
 * @author Mohamed Sherif
 */
@Dao
public interface DetoxPeriodDao {

    @Insert
    void insert(DetoxPeriod detoxPeriod);

    @Update
    void update(DetoxPeriod detoxPeriod);

    @Delete
    void delete(DetoxPeriod detoxPeriod);

    @Query("SELECT id FROM DetoxPeriod WHERE id=:id")
    LiveData<DetoxPeriod> get(int id);

}
