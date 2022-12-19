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

    /**
     * a function that gets the id of detox period to remove it from the detox periods table.
     *
     * @param id represent the id of a detox period to be removed.
     */
    @Query("DELETE FROM detox_periods_table WHERE id=:id")
    void delete(Integer id);

    /**
     * a function that gets the id of a detox period.
     *
     * @param id represent the id of a given detox period.
     *
     * @return live data of detox period from detox periods table.
     */
    @Query("SELECT * FROM detox_periods_table WHERE id=:id")
    LiveData<DetoxPeriod> get(Integer id);

    /**
     * a function that gets the id of a detox period.
     *
     * @param id represent the id of a given detox period.
     *
     * @return live data of detox period with its group from detox periods table.
     */
    @Transaction
    @Query("SELECT * FROM detox_periods_table WHERE id=:id")
    LiveData<DetoxPeriodAndGroup> getWithGroup(Integer id);

    /**
     * a function that gets the id of a detox period.
     *
     * @param id represent the id of a given detox period.
     *
     * @return live data of the detox period and its group with whitelisted
     * apps from detox periods table.
     */
    @Transaction
    @Query("SELECT * FROM detox_periods_table WHERE id=:id")
    LiveData<DetoxPeriodAndGroupWithWhitelistedApps>
    getAndGroupWithWhitelistedApps(Integer id);
}
