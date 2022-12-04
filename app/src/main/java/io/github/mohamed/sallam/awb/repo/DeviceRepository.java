package io.github.mohamed.sallam.awb.repo;

import android.app.Application;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.UUID;

import io.github.mohamed.sallam.awb.db.UserDatabase;
import io.github.mohamed.sallam.awb.db.dao.DeviceDao;
import io.github.mohamed.sallam.awb.db.entity.Device;
import io.github.mohamed.sallam.awb.db.relationship.DeviceWithGroups;

public class DeviceRepository {
    private DeviceDao deviceDao;

    public DeviceRepository(Application application) {
        UserDatabase db = UserDatabase.getInstance(application);
        deviceDao = db.deviceDao();
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
}
