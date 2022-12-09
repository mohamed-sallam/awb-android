package io.github.mohamed.sallam.awb.repo;

import androidx.lifecycle.LiveData;
import io.github.mohamed.sallam.awb.db.entity.DetoxPeriod;
import io.github.mohamed.sallam.awb.db.relationship.DetoxPeriodAndGroup;
import io.github.mohamed.sallam.awb.db.relationship.DetoxPeriodAndGroupWithBlockedApps;

public interface IDetoxPeriodRepository extends IRepository<DetoxPeriod, Integer> {
    void update(DetoxPeriod detoxPeriod);
    LiveData<DetoxPeriod> get(int id);
    LiveData<DetoxPeriodAndGroup> getDetoxPeriodAndGroup(int id);
//    LiveData<DetoxPeriodAndGroupWithBlockedApps>
//    getWithGroupWithBlockedApps(int id);  //TODO implement on DAO
}
