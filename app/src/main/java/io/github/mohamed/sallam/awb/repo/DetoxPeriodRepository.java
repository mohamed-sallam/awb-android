package io.github.mohamed.sallam.awb.repo;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import io.github.mohamed.sallam.awb.db.UserDatabase;
import io.github.mohamed.sallam.awb.db.dao.DetoxPeriodDao;
import io.github.mohamed.sallam.awb.db.entity.DetoxPeriod;
import io.github.mohamed.sallam.awb.db.entity.WhitelistedApp;

/**
 * {@inheritDoc}
 *
 * {@link IDetoxPeriodRepository}
 *
 * @author Abdalrhman Hemida
 * @author Mohamed Yehia
 */
public class DetoxPeriodRepository implements IDetoxPeriodRepository {
    private final DetoxPeriodDao detoxPeriodDao;
    private final LiveData<DetoxPeriod> detoxPeriod;
    private LiveData<List<WhitelistedApp>> whitelistedAppsOfCurrentDetoxPeriod;

    /**
     * Instantiates an object from `detoxPeriodDao`.
     *
     * @param application is the context where The Application class in Android
     * is the base class within an Android app that contains all other
     * components such as activities and services.
     */
    public DetoxPeriodRepository(Application application) {
        UserDatabase db = UserDatabase.getInstance(application);
        detoxPeriodDao = db.detoxPeriodDao();
        detoxPeriod = detoxPeriodDao.get();
        whitelistedAppsOfCurrentDetoxPeriod = detoxPeriodDao.getWhitelistedAppsOfCurrentDetoxPeriod();
    }

    public DetoxPeriodRepository(DetoxPeriodDao detoxPeriodDao){
        this.detoxPeriodDao = detoxPeriodDao;
        this.detoxPeriod = null;
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

    public LiveData<List<WhitelistedApp>> getWhitelistedAppsOfCurrentDetoxPeriod() {
        return whitelistedAppsOfCurrentDetoxPeriod;
    }
}
