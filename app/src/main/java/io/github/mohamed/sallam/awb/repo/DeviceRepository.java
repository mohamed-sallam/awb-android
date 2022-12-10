package io.github.mohamed.sallam.awb.repo;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.UUID;

import io.github.mohamed.sallam.awb.db.UserDatabase;
import io.github.mohamed.sallam.awb.db.dao.DeviceDao;
import io.github.mohamed.sallam.awb.db.dao.GroupDao;
import io.github.mohamed.sallam.awb.db.entity.Device;
import io.github.mohamed.sallam.awb.db.entity.Group;
import io.github.mohamed.sallam.awb.db.relationship.DeviceWithGroups;

public class DeviceRepository implements IDeviceRepository {
    private DeviceDao deviceDao;
    private GroupDao groupDao;

    public DeviceRepository(Application application) {
        UserDatabase db = UserDatabase.getInstance(application);
        deviceDao = db.deviceDao();
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


    public void delete(UUID deviceUuid) {
        UserDatabase.databaseWriteExecutor.execute(new Runnable(){
            @Override
            public void run(){
                deviceDao.delete(deviceUuid);
            }
        });
    }

    public void generateUuid(UUID oldDeviceUuid) {
        UserDatabase.databaseWriteExecutor.execute(new Runnable(){
            @Override
            public void run(){
                UUID newDeviceUuid = UUID.randomUUID();
                groupDao.replaceDeviceUuid(oldDeviceUuid, newDeviceUuid);
                deviceDao.setUuid(oldDeviceUuid, newDeviceUuid);
            }
        });
    }

    public LiveData<List<Device>> getAll() {
        return deviceDao.getAll();
    }

    public LiveData<List<DeviceWithGroups>> getAllDevicesWithGroups() {
        return deviceDao.getAllWithGroups();
    }
}
