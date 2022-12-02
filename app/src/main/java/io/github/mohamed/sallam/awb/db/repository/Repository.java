package io.github.mohamed.sallam.awb.db.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.RoomDatabase;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;
import java.util.UUID;

import io.github.mohamed.sallam.awb.db.dao.BlockedAppDao;
import io.github.mohamed.sallam.awb.db.dao.DetoxPeriodDao;
import io.github.mohamed.sallam.awb.db.dao.DeviceDao;
import io.github.mohamed.sallam.awb.db.dao.GroupDao;
import io.github.mohamed.sallam.awb.db.entity.BlockedApp;
import io.github.mohamed.sallam.awb.db.entity.DetoxPeriod;
import io.github.mohamed.sallam.awb.db.entity.Device;
import io.github.mohamed.sallam.awb.db.entity.Group;
import io.github.mohamed.sallam.awb.db.relationship.DetoxPeriodAndGroup;
import io.github.mohamed.sallam.awb.db.relationship.DeviceWithGroups;
import io.github.mohamed.sallam.awb.db.relationship.GroupWithBlockedApps;

public class Repository {
    BlockedAppDao blockedAppDao;
    DetoxPeriodDao detoxPeriodDao;
    DeviceDao deviceDao;
    GroupDao groupDao;

    public Repository(Application application) {
        UserDatabase db = UserDatabase.getDatabase(application);
        blockedAppDao = db.blockedAppDao();
        detoxPeriodDao = db.detoxPeriodDao();
        deviceDao = db.deviceDao();
        groupDao = db.groupDao();
    }

    // BlockedAppDao
    public void insert(BlockedApp blockedApp) {
        UserDatabase.databaseWriteExecutor.execute(new Runnable(){
            @Override
            public void run(){
                blockedAppDao.insert(blockedApp);
            }
        });
    }

    public void delete(BlockedApp blockedApp) {
        UserDatabase.databaseWriteExecutor.execute(new Runnable(){
            @Override
            public void run(){
                blockedAppDao.delete(blockedApp);
            }
        });
    }

    public LiveData<List<BlockedApp>> getAll() {
        blockedAppDao.getAll();
    }

    public void clone(UUID sourceGroupUuid, UUID destinationGroupUuid) {

    }

    // DetoxPeriodDao
    public void insert(DetoxPeriod detoxPeriod) {
        UserDatabase.databaseWriteExecutor.execute(new Runnable(){
            @Override
            public void run(){
                detoxPeriodDao.insert(detoxPeriod);
            }
        });
    }

    public void update(DetoxPeriod detoxPeriod) {
        UserDatabase.databaseWriteExecutor.execute(new Runnable(){
            @Override
            public void run(){
                detoxPeriodDao.update(detoxPeriod);
            }
        });
    }

    public void delete(DetoxPeriod detoxPeriod) {
        UserDatabase.databaseWriteExecutor.execute(new Runnable(){
            @Override
            public void run(){
                detoxPeriodDao.delete(detoxPeriod);
            }
        });
    }

    public LiveData<DetoxPeriod> get(int id) {
        detoxPeriodDao.get(id);
    }

    public LiveData<DetoxPeriodAndGroup> getWithGroup(int id) {
        detoxPeriodDao.getWithGroup(id);
    }

    //DeviceDao

    public void insert(Device device) {
        UserDatabase.databaseWriteExecutor.execute(new Runnable(){
            @Override
            public void run(){
                deviceDao.insert(device);
            }
        });
    }

    public void update(Device device) {
        UserDatabase.databaseWriteExecutor.execute(new Runnable(){
            @Override
            public void run(){
                deviceDao.update(device);
            }
        });
    }

    public void delete(Device device) {
        UserDatabase.databaseWriteExecutor.execute(new Runnable(){
            @Override
            public void run(){
                deviceDao.delete(device);
            }
        });
    }

    public void setUuid(UUID oldUuid, UUID newUuid) {

    }

    public LiveData<List<Device>> getAll() {
        deviceDao.getAll();
    }

    public LiveData<List<DeviceWithGroups>> getAllWithGroups() {
        deviceDao.getAllWithGroups();
    }

    //GroupDao
    public void insert(Group group) {
        UserDatabase.databaseWriteExecutor.execute(new Runnable(){
            @Override
            public void run(){
                groupDao.insert(group);
            }
        });
    }

    public void update(Group group) {
        UserDatabase.databaseWriteExecutor.execute(new Runnable(){
            @Override
            public void run(){
                groupDao.update(group);
            }
        });
    }

    public void delete(Group group) {
        UserDatabase.databaseWriteExecutor.execute(new Runnable(){
            @Override
            public void run(){
                groupDao.delete(group);
            }
        });
    }

    public LiveData<List<Group>> getAll() {
        groupDao.getAll();
    }

    public LiveData<List<GroupWithBlockedApps>> getAllWithBlockedApps() {
        groupDao.getAllWithBlockedApps();
    }

}
