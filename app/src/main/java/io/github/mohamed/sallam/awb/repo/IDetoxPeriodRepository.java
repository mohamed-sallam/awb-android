package io.github.mohamed.sallam.awb.repo;

import androidx.lifecycle.LiveData;

import io.github.mohamed.sallam.awb.db.entity.DetoxPeriod;
import io.github.mohamed.sallam.awb.db.relationship.DetoxPeriodAndGroup;
import io.github.mohamed.sallam.awb.db.relationship.DetoxPeriodAndGroupWithWhitelistedApps;

/**
 * {@link IRepository}
 *
 * `IDetoxPeriodRepository` interface is responsible for handling and declaring
 * detox period operations (insert - update - delete - getters). On class
 * `DetoxPeriodRepository` we add the full logic and implementation to execute
 * a specific operation by using DAOs methods.
 *
 * @author Abdalrhman Hemida
 * @author Mohamed Yehia
 */
public interface IDetoxPeriodRepository extends IRepository<DetoxPeriod> {

    /**
     * Updates detox periods in database.
     *
     * @param detoxPeriod object of the blocking period.
     */
    void update(DetoxPeriod detoxPeriod);

    /**
     * Gets detox periods by id as a live data.
     *
     * @return Detox period from database by using `detoxPeriodDao`.
     */
    LiveData<DetoxPeriod> get();

    /**
     * Gets detox-period with group using a relationship `DetoxPeriodAndGroup`.
     *
     * @return live data detox period with a group specified to it.
     */
    LiveData<DetoxPeriodAndGroup> getDetoxPeriodAndGroup();

    /**
     * Gets detox-period with group including its applications using
     * relationship `DetoxPeriodAndGroupWithWhitelistedApps`.
     *
     * @return detox period for a group with its Whitelisted applications.
     */
    LiveData<DetoxPeriodAndGroupWithWhitelistedApps>
    getAndGroupWithWhitelistedApps();
}
