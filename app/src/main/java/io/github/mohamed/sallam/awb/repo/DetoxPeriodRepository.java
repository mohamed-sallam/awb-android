package io.github.mohamed.sallam.awb.repo;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

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
    private final LiveData<DetoxPeriod> detoxPeriods;
    private LiveData<List<WhitelistedApp>> whitelistedAppsOfCurrentDetoxPeriods;

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
        detoxPeriods = detoxPeriodDao.get();
        whitelistedAppsOfCurrentDetoxPeriods = detoxPeriodDao.getWhitelistedAppsOfCurrentDetoxPeriod();
    }

    public DetoxPeriodRepository(DetoxPeriodDao detoxPeriodDao, DetoxPeriod detoxPeriod,
                                 @NonNull LiveData<List<WhitelistedApp>> liveDataWhitelistedAppsOfCurrentDetoxPeriods){
        this.detoxPeriodDao = detoxPeriodDao;
        this.detoxPeriods = new MutableLiveData<>(detoxPeriod);
        this.whitelistedAppsOfCurrentDetoxPeriods = liveDataWhitelistedAppsOfCurrentDetoxPeriods;
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
        return detoxPeriods;
    }

    public LiveData<List<WhitelistedApp>> getWhitelistedAppsOfCurrentDetoxPeriod() {
        return whitelistedAppsOfCurrentDetoxPeriods;
    }
}
