package io.github.mohamed.sallam.awb.repo;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.UUID;

import io.github.mohamed.sallam.awb.db.entity.Device;
import io.github.mohamed.sallam.awb.db.relationship.DeviceWithGroups;

public interface IDeviceRepository extends IRepository<Device> {
    void update(Device device);
    void generateUuid(UUID oldUuid);
    LiveData<List<Device>> getAll();
    LiveData<List<DeviceWithGroups>> getAllWithGroups();  // TODO maybe deleted
}
