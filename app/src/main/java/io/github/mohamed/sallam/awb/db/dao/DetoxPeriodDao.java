package io.github.mohamed.sallam.awb.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import io.github.mohamed.sallam.awb.db.entity.DetoxPeriod;
import io.github.mohamed.sallam.awb.db.relationship.DetoxPeriodAndGroup;
import io.github.mohamed.sallam.awb.db.relationship.DetoxPeriodAndGroupWithWhitelistedApps;

/**
 * Detox Period Data Access Object.
 *
 * @author Mohamed Sherif
 * @author Mohamed Sallam
 */
@Dao
public interface DetoxPeriodDao {
    @Insert
    void insert(DetoxPeriod detoxPeriod);

    @Update
    void update(DetoxPeriod detoxPeriod);

    @Query("DELETE FROM detox_periods_table WHERE id=:id")
    void delete(Integer id);

    @Query("SELECT * FROM detox_periods_table WHERE id=:id")
    LiveData<DetoxPeriod> get(Integer id);

    @Transaction
    @Query("SELECT * FROM detox_periods_table WHERE id=:id")
    LiveData<DetoxPeriodAndGroup> getWithGroup(Integer id);

    @Transaction
    @Query("SELECT * FROM detox_periods_table WHERE id=:id")
    LiveData<DetoxPeriodAndGroupWithWhitelistedApps>
    getAndGroupWithWhitelistedApps(Integer id);
}
