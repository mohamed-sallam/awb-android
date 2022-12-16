package io.github.mohamed.sallam.awb.repo;

import androidx.lifecycle.LiveData;

import io.github.mohamed.sallam.awb.db.entity.DetoxPeriod;
import io.github.mohamed.sallam.awb.db.relationship.DetoxPeriodAndGroup;
import io.github.mohamed.sallam.awb.db.relationship.DetoxPeriodAndGroupWithWhitelistedApps;

public interface IDetoxPeriodRepository extends IRepository<DetoxPeriod> {
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
    void update(DetoxPeriod detoxPeriod);
    LiveData<DetoxPeriod> get(Integer id);
    LiveData<DetoxPeriodAndGroup> getDetoxPeriodAndGroup(Integer id);
    LiveData<DetoxPeriodAndGroupWithWhitelistedApps>
    getAndGroupWithWhitelistedApps(Integer id);
}
