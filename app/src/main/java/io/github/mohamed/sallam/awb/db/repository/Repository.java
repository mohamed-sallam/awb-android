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
    public void insertBlockedApp(BlockedApp blockedApp) {
        UserDatabase.databaseWriteExecutor.execute(new Runnable(){
            @Override
            public void run(){
                blockedAppDao.insert(blockedApp);
            }
        });
    }

    public void deleteBlockedApp(BlockedApp blockedApp) {
        UserDatabase.databaseWriteExecutor.execute(new Runnable(){
            @Override
            public void run(){
                blockedAppDao.delete(blockedApp);
            }
        });
    }

    public LiveData<List<BlockedApp>> getAllBlockedApp() {
        return blockedAppDao.getAll();
    }

    public void cloneBlockedAppToGroup
            (UUID sourceGroupUuid, UUID destinationGroupUuid) {
        UserDatabase.databaseWriteExecutor.execute(new Runnable(){
            @Override
            public void run(){
                blockedAppDao.clone(sourceGroupUuid, destinationGroupUuid);
            }
        });
    }

    // DetoxPeriodDao
    public void insertDetoxPeriod(DetoxPeriod detoxPeriod) {
        UserDatabase.databaseWriteExecutor.execute(new Runnable(){
            @Override
            public void run(){
                detoxPeriodDao.insert(detoxPeriod);
            }
        });
    }

    public void updateDetoxPeriod(DetoxPeriod detoxPeriod) {
        UserDatabase.databaseWriteExecutor.execute(new Runnable(){
            @Override
            public void run(){
                detoxPeriodDao.update(detoxPeriod);
            }
        });
    }

    public void deleteDetoxPeriod(DetoxPeriod detoxPeriod) {
        UserDatabase.databaseWriteExecutor.execute(new Runnable(){
            @Override
            public void run(){
                detoxPeriodDao.delete(detoxPeriod);
            }
        });
    }

    public LiveData<DetoxPeriod> getDetoxPeriod(int id) {
        return detoxPeriodDao.get(id);
    }

    public LiveData<DetoxPeriodAndGroup> getDetoxPeriodWithGroup(int id) {
        return detoxPeriodDao.getWithGroup(id);
    }

    //DeviceDao
    public void insertDevice(Device device) {
        UserDatabase.databaseWriteExecutor.execute(new Runnable(){
            @Override
            public void run(){
                deviceDao.insert(device);
            }
        });
    }

    public void updateDevice(Device device) {
        UserDatabase.databaseWriteExecutor.execute(new Runnable(){
            @Override
            public void run(){
                deviceDao.update(device);
            }
        });
    }

    public void deleteDevice(Device device) {
        UserDatabase.databaseWriteExecutor.execute(new Runnable(){
            @Override
            public void run(){
                deviceDao.delete(device);
            }
        });
    }

    public void setUuidToDevice(UUID oldUuid, UUID newUuid) {
        UserDatabase.databaseWriteExecutor.execute(new Runnable(){
            @Override
            public void run(){
                deviceDao.setUuid(oldUuid,newUuid);
            }
        });
    }

    public LiveData<List<Device>> getAllDevices() {
        return deviceDao.getAll();
    }

    public LiveData<List<DeviceWithGroups>> getAllDevicesWithGroups() {
        return deviceDao.getAllWithGroups();
    }

    //GroupDao
    public void insertGroup(Group group) {
        UserDatabase.databaseWriteExecutor.execute(new Runnable(){
            @Override
            public void run(){
                groupDao.insert(group);
            }
        });
    }

    public void updateGroup(Group group) {
        UserDatabase.databaseWriteExecutor.execute(new Runnable(){
            @Override
            public void run(){
                groupDao.update(group);
            }
        });
    }

    public void deleteGroup(Group group) {
        UserDatabase.databaseWriteExecutor.execute(new Runnable(){
            @Override
            public void run(){
                groupDao.delete(group);
            }
        });
    }

    public LiveData<List<Group>> getAllGroup() {
        return groupDao.getAll();
    }

    public LiveData<List<GroupWithBlockedApps>> getAllGroupWithBlockedApps() {
        return groupDao.getAllWithBlockedApps();
    }
}
