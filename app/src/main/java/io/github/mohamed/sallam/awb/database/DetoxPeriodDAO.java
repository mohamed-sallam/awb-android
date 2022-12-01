package io.github.mohamed.sallam.awb.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

/**
 * Detox Period Data Access Object
 * @author Mohamed Sherif
 */
@Dao
public interface DetoxPeriodDAO {

    @Insert
    void insertDetoxPeriod(DetoxPeriod detoxPeriod);

    @Update
    void updateDetoxPeriod(DetoxPeriod detoxPeriod);

    @Delete
    void deleteDetoxPeriod(DetoxPeriod detoxPeriod);

    @Query("SELECT id FROM DetoxPeriod WHERE id=:id")
    DetoxPeriod get(int id);

}
