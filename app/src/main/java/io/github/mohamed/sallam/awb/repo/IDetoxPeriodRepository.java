package io.github.mohamed.sallam.awb.repo;

import androidx.lifecycle.LiveData;
import io.github.mohamed.sallam.awb.db.entity.DetoxPeriod;
import io.github.mohamed.sallam.awb.db.relationship
        .DetoxPeriodAndGroupWithBlockedApps;

public interface IDetoxPeriodRepository extends IRepository<DetoxPeriod> {
    void delete(int id);
    void update(DetoxPeriod detoxPeriod);
    LiveData<DetoxPeriod> get(int id);
    LiveData<DetoxPeriodAndGroupWithBlockedApps>
        getDetoxPeriodAndGroupWithBlockedApps(int id);  //TODO implement on DAO
}
