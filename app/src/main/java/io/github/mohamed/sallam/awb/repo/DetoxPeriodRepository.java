package io.github.mohamed.sallam.awb.repo;

import android.app.Application;
import androidx.lifecycle.LiveData;
import io.github.mohamed.sallam.awb.db.UserDatabase;
import io.github.mohamed.sallam.awb.db.dao.DetoxPeriodDao;
import io.github.mohamed.sallam.awb.db.entity.DetoxPeriod;
import io.github.mohamed.sallam.awb.db.relationship.DetoxPeriodAndGroup;
import io.github.mohamed.sallam.awb.db.relationship.
        DetoxPeriodAndGroupWithWhitelistedApps;

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
     * {@inheritDoc}
     *
     * @param id the blocking period id, is the unique identifier for the period.
     */
    public void delete(Integer id) {
        UserDatabase.databaseWriteExecutor.execute(
                () -> detoxPeriodDao.delete(id)
        );
    }

    public LiveData<DetoxPeriod> get(Integer id) {
        return detoxPeriodDao.get(id);
    }

    public LiveData<DetoxPeriodAndGroup> getDetoxPeriodAndGroup(Integer id) {
        return detoxPeriodDao.getWithGroup(id);
    }

    public LiveData<DetoxPeriodAndGroupWithWhitelistedApps>
    getAndGroupWithWhitelistedApps(Integer id) {
        return detoxPeriodDao.getAndGroupWithWhitelistedApps(id);
    }
}
