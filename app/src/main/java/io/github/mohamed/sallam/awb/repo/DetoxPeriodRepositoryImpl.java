package io.github.mohamed.sallam.awb.repo;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import io.github.mohamed.sallam.awb.db.UserDatabase;
import io.github.mohamed.sallam.awb.db.dao.DetoxPeriodDao;
import io.github.mohamed.sallam.awb.db.entity.DetoxPeriod;
import io.github.mohamed.sallam.awb.db.entity.WhitelistedApp;
import io.github.mohamed.sallam.awb.db.relationship.DetoxPeriodAndGroup;
import io.github.mohamed.sallam.awb.db.relationship.DetoxPeriodAndGroupWithWhitelistedApps;

/**
 * {@inheritDoc}
 *
 * {@link DetoxPeriodRepository}
 *
 * @author Abdalrhman Hemida
 * @author Mohamed Yehia
 */
public class DetoxPeriodRepositoryImpl implements DetoxPeriodRepository {
    private final DetoxPeriodDao detoxPeriodDao;
    private final LiveData<DetoxPeriodAndGroupWithWhitelistedApps> detoxPeriodAndGroupWithWhitelistedApps;
    private final LiveData<DetoxPeriodAndGroup> detoxPeriodAndGroup;
    private final LiveData<DetoxPeriod> detoxPeriod;
    private LiveData<List<WhitelistedApp>> whitelistedAppsOfCurrentDetoxPeriod;

    /**
     * Instantiates an object from `detoxPeriodDao`.
     *
     * @param application is the context where The Application class in Android
     * is the base class within an Android app that contains all other
     * components such as activities and services.
     */
    public DetoxPeriodRepositoryImpl(Application application) {
        UserDatabase db = UserDatabase.getInstance(application);
        detoxPeriodDao = db.detoxPeriodDao();
        detoxPeriodAndGroupWithWhitelistedApps = detoxPeriodDao.getAndGroupWithWhitelistedApps();
        detoxPeriodAndGroup = detoxPeriodDao.getWithGroup();
        detoxPeriod = detoxPeriodDao.get();
        whitelistedAppsOfCurrentDetoxPeriod = detoxPeriodDao.getWhitelistedAppsOfCurrentDetoxPeriod();
    }

    /**
     * {@inheritDoc}
     *
     * @param detoxPeriod object of the blocking period.
     */
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

    /**
     * Deletes a specific detox period from database. We use it to remove detox
     * period for a specific group.
     */
    public void delete() {
        UserDatabase.databaseWriteExecutor.execute(
                detoxPeriodDao::delete
        );
    }

    public LiveData<DetoxPeriod> get() {
        return detoxPeriod;
    }

    public LiveData<DetoxPeriodAndGroup>
    getDetoxPeriodAndGroup() {
        return detoxPeriodAndGroup;
    }

    public LiveData<DetoxPeriodAndGroupWithWhitelistedApps>
    getAndGroupWithWhitelistedApps() {
        return detoxPeriodAndGroupWithWhitelistedApps;
    }

    public LiveData<List<WhitelistedApp>> getWhitelistedAppsOfCurrentDetoxPeriod() {
        return whitelistedAppsOfCurrentDetoxPeriod;
    }
}
