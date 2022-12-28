package io.github.mohamed.sallam.awb.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

import io.github.mohamed.sallam.awb.db.entity.DetoxPeriod;
import io.github.mohamed.sallam.awb.db.entity.WhitelistedApp;
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

    @Query("DELETE FROM detox_periods_table")
    void delete();

    @Query("SELECT * FROM detox_periods_table WHERE id = (SELECT MAX(id) FROM detox_periods_table) LIMIT 1")
    LiveData<DetoxPeriod> get();

    @Transaction
    @Query("SELECT * FROM detox_periods_table WHERE id = (SELECT MAX(id) FROM detox_periods_table) LIMIT 1")
    LiveData<DetoxPeriodAndGroup> getWithGroup();

    @Transaction
    @Query("SELECT * FROM detox_periods_table WHERE id = (SELECT MAX(id) FROM detox_periods_table) LIMIT 1")
    LiveData<DetoxPeriodAndGroupWithWhitelistedApps>
    getAndGroupWithWhitelistedApps();

    @Query("SELECT * FROM whitelisted_apps_table WHERE groupUuid = (SELECT groupUuid FROM detox_periods_table WHERE id = (SELECT MAX(id) FROM detox_periods_table) LIMIT 1)")
    LiveData<List<WhitelistedApp>> getWhitelistedAppsOfCurrentDetoxPeriod();
}
