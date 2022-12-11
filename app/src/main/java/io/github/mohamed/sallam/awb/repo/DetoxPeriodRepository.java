package io.github.mohamed.sallam.awb.repo;

import android.app.Application;

import androidx.lifecycle.LiveData;

import io.github.mohamed.sallam.awb.db.UserDatabase;
import io.github.mohamed.sallam.awb.db.dao.DetoxPeriodDao;
import io.github.mohamed.sallam.awb.db.entity.DetoxPeriod;
import io.github.mohamed.sallam.awb.db.relationship.DetoxPeriodAndGroup;
import io.github.mohamed.sallam.awb.db.relationship.DetoxPeriodAndGroupWithWhitelistedApps;

public class DetoxPeriodRepository implements IDetoxPeriodRepository {
    private final DetoxPeriodDao detoxPeriodDao;

    public DetoxPeriodRepository(Application application) {
        UserDatabase db = UserDatabase.getInstance(application);
        detoxPeriodDao = db.detoxPeriodDao();
    }

    // DetoxPeriodDao
    public void insert(DetoxPeriod detoxPeriod) {
        UserDatabase.databaseWriteExecutor.execute(
                () -> detoxPeriodDao.insert(detoxPeriod)
        );
    }

    public void update(DetoxPeriod detoxPeriod) {
        UserDatabase.databaseWriteExecutor.execute(
                () -> detoxPeriodDao.update(detoxPeriod)
        );
    }

    public void delete() {
        UserDatabase.databaseWriteExecutor.execute(
                () -> detoxPeriodDao.delete()
        );
    }

    public LiveData<DetoxPeriod> get() {
        return detoxPeriodDao.get();
    }

    public LiveData<DetoxPeriodAndGroup>
    getDetoxPeriodAndGroup() {
        return detoxPeriodDao.getWithGroup();
    }

    public LiveData<DetoxPeriodAndGroupWithWhitelistedApps>
    getAndGroupWithWhitelistedApps() {
        return detoxPeriodDao.getAndGroupWithWhitelistedApps();
    }

}
