package io.github.mohamed.sallam.awb.repo;

import android.app.Application;

import androidx.lifecycle.LiveData;

import io.github.mohamed.sallam.awb.db.UserDatabase;
import io.github.mohamed.sallam.awb.db.dao.DetoxPeriodDao;
import io.github.mohamed.sallam.awb.db.entity.DetoxPeriod;
import io.github.mohamed.sallam.awb.db.relationship.DetoxPeriodAndGroup;
import io.github.mohamed.sallam.awb.db.relationship.DetoxPeriodAndGroupWithWhitelistedApps;

/**
 * `DetoxPeriodRepository` class is responsible for handling and implementing
 * detox period operations, and adding the full logic to execute a specific
 * operation by using DAOs methods.
 * Repository pattern apply a design principle 'separation of concerns' which
 * makes our classes unit testable. It apples Principle of Dependency Inversion
 * (or code to abstractions, not concretions). to make out code more robust to
 * changes.
 *
 * Using 'lamda expression' over the whole methods to optimize the code.
 * Lambda Expressions were added in Java 8
 *
 * why to use lamda expression?
 * Makes our code more readable and shorter. Instead of instantiation an
 * anonymous inner class that implements the abstract method run() on the
 * interface 'Runnable', we use lamdad expression to do that.
 *
 * for example: instead of writing
 * UserDatabase.databaseWriteExecutor.execute(new Runnable() {
 *             @Override
 *             public void run() {
 *                 detoxPeriodDao.insert(detoxPeriod);
 *             }
 *         });
 * we write that:
 * UserDatabase.databaseWriteExecutor.execute(
 *                 () -> detoxPeriodDao.insert(detoxPeriod)
 *         );
 *
 * source: https://code.tutsplus.com/tutorials/
 * java-8-for-android-cleaner-code-with-lambda-expressions--cms-29661
 *
 * @author Abdalrhman Hemida
 * @author Mohamed Yehia
 */
public class DetoxPeriodRepository implements IDetoxPeriodRepository {
    private final DetoxPeriodDao detoxPeriodDao;

    /**
     * Constructor to instantiate an object from `detoxPeriodDao`.
     *
     * @param application is the context where The Application class in Android
     * is the base class within an Android app that contains all other
     * components such as activities and services.
     */
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

    public void delete(Integer id) {
        UserDatabase.databaseWriteExecutor.execute(
                () -> detoxPeriodDao.delete(id)
        );
    }

    public LiveData<DetoxPeriod> get(Integer id) {
        return detoxPeriodDao.get(id);
    }

    public LiveData<DetoxPeriodAndGroup>
    getDetoxPeriodAndGroup(Integer id) {
        return detoxPeriodDao.getWithGroup(id);
    }

    public LiveData<DetoxPeriodAndGroupWithWhitelistedApps>
    getAndGroupWithWhitelistedApps(Integer id) {
        return detoxPeriodDao.getAndGroupWithWhitelistedApps(id);
    }

}
