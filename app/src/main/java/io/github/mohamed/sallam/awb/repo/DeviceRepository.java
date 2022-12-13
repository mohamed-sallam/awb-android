package io.github.mohamed.sallam.awb.repo;

import android.app.Application;
import android.os.Build;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.List;
import java.util.UUID;

import io.github.mohamed.sallam.awb.db.UserDatabase;
import io.github.mohamed.sallam.awb.db.dao.DeviceDao;
import io.github.mohamed.sallam.awb.db.dao.GroupDao;
import io.github.mohamed.sallam.awb.db.entity.Device;
import io.github.mohamed.sallam.awb.db.entity.Group;
import io.github.mohamed.sallam.awb.db.relationship.DeviceWithGroups;

public class DeviceRepository implements IDeviceRepository {
    private final DeviceDao deviceDao;
    private final GroupDao groupDao;
    private final LiveData<List<Device>> devices;
    private final LiveData<List<DeviceWithGroups>> devicesWithGroups;
    private final LiveData<Device> thisDevice;

    public DeviceRepository(Application application) {
        UserDatabase db = UserDatabase.getInstance(application);
        deviceDao = db.deviceDao();
        groupDao = db.groupDao();
        devices = deviceDao.getAll();
        devicesWithGroups = deviceDao.getAllWithGroups();
        thisDevice = deviceDao.getThisDevice();
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
        return devices;
    }

    public LiveData<List<DeviceWithGroups>> getAllWithGroups() {
        return devicesWithGroups;
    }

    public LiveData<Device> getThisDevice() {
        return thisDevice;
    }
}
