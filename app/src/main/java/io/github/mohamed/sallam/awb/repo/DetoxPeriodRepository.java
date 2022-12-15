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

    /**
     * Method to insert detox, blocking, period in the database.
     * @param detoxPeriod object of the blocking period.
     */
    public void insert(DetoxPeriod detoxPeriod) {
        UserDatabase.databaseWriteExecutor.execute(
                () -> detoxPeriodDao.insert(detoxPeriod)
        );
    }

    /**
     * Method to update detox, blocking, period.
     * @param detoxPeriod object of the blocking period
     */
    public void update(DetoxPeriod detoxPeriod) {
        UserDatabase.databaseWriteExecutor.execute(
                () -> detoxPeriodDao.update(detoxPeriod)
        );
    }

    /**
     * Method to delete detox, blocking, period.
     *
     * @param id the blocking period id, is the unique identifier for the period.
     */
    public void delete(Integer id) {
        UserDatabase.databaseWriteExecutor.execute(
                () -> detoxPeriodDao.delete(id)
        );
    }

    /**
     * Method to get detox, blocking, period by id as a live data.
     * LiveData is an observable data holder class. Unlike a regular observable
     * so any change on database will be affecting UI.
     *
     * @param id the blocking period id, is the unique identifier for the period.
     *
     * @return Detox period from database by using `detoxPeriodDao`.
     */
    public LiveData<DetoxPeriod> get(Integer id) {
        return detoxPeriodDao.get(id);
    }

    /**
     * Method to get detox-period with group using a relationship `DetoxPeriodAndGroup`
     *
     * @param id the blocking period id, is the unique identifier for the period.
     *
     * @return Detox period with a group specified to it.
     */
    public LiveData<DetoxPeriodAndGroup>
    getDetoxPeriodAndGroup(Integer id) {
        return detoxPeriodDao.getWithGroup(id);
    }

    /**
     * Method to get detox-period with group including its applications using
     * relationship `DetoxPeriodAndGroupWithWhitelistedApps`
     *
     * @param id the blocking period id, is the unique identifier for the period.
     *
     * @return Detox period for a group with its Whitelisted applications.
     */
    public LiveData<DetoxPeriodAndGroupWithWhitelistedApps>
    getAndGroupWithWhitelistedApps(Integer id) {
        return detoxPeriodDao.getAndGroupWithWhitelistedApps(id);
    }

}
