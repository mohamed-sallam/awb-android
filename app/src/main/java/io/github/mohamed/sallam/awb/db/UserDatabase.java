package io.github.mohamed.sallam.awb.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.github.mohamed.sallam.awb.db.dao.BlockedAppDao;
import io.github.mohamed.sallam.awb.db.dao.DetoxPeriodDao;
import io.github.mohamed.sallam.awb.db.dao.DeviceDao;
import io.github.mohamed.sallam.awb.db.dao.GroupDao;
import io.github.mohamed.sallam.awb.db.entity.BlockedApp;
import io.github.mohamed.sallam.awb.db.entity.DetoxPeriod;
import io.github.mohamed.sallam.awb.db.entity.Device;
import io.github.mohamed.sallam.awb.db.entity.Group;

/**
 * Our Internal Database
 *
 * @Source https://www.techotopia.com/index.php/An_Android_Room_Database_and_Repository_Tutorial
 *
 * @author Mohamed Sherif
 */
@Database(entities = {BlockedApp.class, DetoxPeriod.class,
        Device.class, Group.class}, version = 1, exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {
    public abstract BlockedAppDao blockedAppDao();
    public abstract DetoxPeriodDao detoxPeriodDao();
    public abstract DeviceDao deviceDao();
    public abstract GroupDao groupDao();
    private static volatile UserDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 2;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static UserDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (UserDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            UserDatabase.class, "user_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}