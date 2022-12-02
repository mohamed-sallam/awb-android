package io.github.mohamed.sallam.awb.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import io.github.mohamed.sallam.awb.db.entity.DetoxPeriod;
import io.github.mohamed.sallam.awb.db.relationship.DetoxPeriodAndGroup;

/**
 * Detox Period Data Access Object.
 *
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

    @Query("SELECT * FROM detox_periods_table WHERE id=:id")
    LiveData<DetoxPeriod> get(int id);

    @Transaction
    @Query("SELECT * FROM detox_periods_table WHERE id=:id")
    LiveData<DetoxPeriodAndGroup> getWithGroup(int id);
}