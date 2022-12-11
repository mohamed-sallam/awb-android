package io.github.mohamed.sallam.awb.db;

import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.github.mohamed.sallam.awb.db.dao.WhitelistedAppDao;
import io.github.mohamed.sallam.awb.db.dao.DetoxPeriodDao;
import io.github.mohamed.sallam.awb.db.dao.DeviceDao;
import io.github.mohamed.sallam.awb.db.dao.GroupDao;
import io.github.mohamed.sallam.awb.db.entity.WhitelistedApp;
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
@Database(entities = {WhitelistedApp.class, DetoxPeriod.class, Device.class,
                      Group.class},
          version = 1,
          exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {
    private static volatile UserDatabase instance;
    public abstract WhitelistedAppDao  whitelistedAppDao();
    public abstract DetoxPeriodDao detoxPeriodDao();
    public abstract DeviceDao      deviceDao();
    public abstract GroupDao       groupDao();
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(2);

    public static synchronized UserDatabase getInstance(final Context context) {
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                                            UserDatabase.class,
                                      "user_database")
                           .addCallback(roomCallback)
                           .build();
        }
        return instance;
    }

    private static final
    RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                Device device = new Device();
                device.name = android.os.Build.MANUFACTURER
                            + android.os.Build.PRODUCT;
                device.thisDevice = true;
                device.operatingSystemName = Build.VERSION_CODES
                                                  .class
                                                  .getFields()[android.os
                                                                      .Build
                                                                      .VERSION
                                                                      .SDK_INT]
                                                  .getName();
                device.operatingSystemType = Device.Os.ANDROID;
                try {
                    device.ipAddressV4 = Inet4Address.getLocalHost()
                                                     .getHostAddress();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                device.secretKey = "1234"; // TODO: use generation function from
                                           // our utils

                instance.deviceDao().insert(device);
            });
        }
    };
}