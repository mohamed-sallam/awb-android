package io.github.mohamed.sallam.awb.repo;

import androidx.lifecycle.LiveData;
import java.util.List;
import java.util.UUID;
import io.github.mohamed.sallam.awb.db.entity.Device;
import io.github.mohamed.sallam.awb.db.relationship.DeviceWithGroups;

/**
 * {@link IRepository}
 *
 * `IDeviceRepository` interface is responsible for handling and declaring
 * device operations (insert - update - delete - generateUuid - getters). On class
 * `DeviceRepository` we add the full logic and implementation to execute
 * a specific operation by using DAOs methods.
 *
 * @author Abdalrhman Hemida
 * @author Mohamed Yehia
 */
public interface IDeviceRepository extends IRepository<Device, UUID> {
    void update(Device device);
    void generateUuid(UUID oldUuid);
    LiveData<List<Device>> getAll();
    LiveData<List<DeviceWithGroups>> getAllWithGroups();  // TODO maybe deleted
}
