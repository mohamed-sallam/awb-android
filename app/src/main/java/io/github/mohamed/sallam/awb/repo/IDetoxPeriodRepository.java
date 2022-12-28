package io.github.mohamed.sallam.awb.repo;

import androidx.lifecycle.LiveData;

import io.github.mohamed.sallam.awb.db.entity.DetoxPeriod;
import io.github.mohamed.sallam.awb.db.relationship.DetoxPeriodAndGroup;
import io.github.mohamed.sallam.awb.db.relationship.DetoxPeriodAndGroupWithWhitelistedApps;

public interface IDetoxPeriodRepository extends IRepository<DetoxPeriod> {
    void update(DetoxPeriod detoxPeriod);
    LiveData<DetoxPeriod> get();
    LiveData<DetoxPeriodAndGroup> getDetoxPeriodAndGroup();
    LiveData<DetoxPeriodAndGroupWithWhitelistedApps>
    getAndGroupWithWhitelistedApps();
}
