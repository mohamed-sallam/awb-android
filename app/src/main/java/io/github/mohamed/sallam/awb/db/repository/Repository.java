package io.github.mohamed.sallam.awb.db.repository;

import android.app.Application;

import androidx.room.RoomDatabase;

import io.github.mohamed.sallam.awb.db.dao.BlockedAppDao;
import io.github.mohamed.sallam.awb.db.dao.DetoxPeriodDao;
import io.github.mohamed.sallam.awb.db.dao.DeviceDao;
import io.github.mohamed.sallam.awb.db.dao.GroupDao;

public class Repository {
    BlockedAppDao blockedAppDao;
    DetoxPeriodDao detoxPeriodDao;
    DeviceDao deviceDao;
    GroupDao groupDao;

    public Repository(Application application){
        UserDatabase db = UserDatabase.getDatabase(application);
        blockedAppDao = db.blockedAppDao();
        detoxPeriodDao = db.detoxPeriodDao();
        deviceDao = db.deviceDao();
        groupDao = db.groupDao();
    }

}
