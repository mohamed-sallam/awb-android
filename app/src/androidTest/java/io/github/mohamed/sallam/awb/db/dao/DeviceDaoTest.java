package io.github.mohamed.sallam.awb.db.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.test.filters.SmallTest;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import io.github.mohamed.sallam.awb.db.UserDatabaseTest;
import io.github.mohamed.sallam.awb.db.entity.Device;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class DeviceDaoTest extends UserDatabaseTest {

    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    @Test
    public void insertAndGetAllDevices() throws InterruptedException {
        List<Device> devices = new ArrayList<>();

        // create a few Device objects
        Device device1 = new Device();
        device1.name = "Device 1";
        device1.thisDevice = true;
        device1.operatingSystemName = "Android";
        device1.operatingSystemType = Device.Os.ANDROID;
        device1.ipAddressV4 = "192.168.1.100";
        device1.secretKey = "secret1";

        Device device2 = new Device();
        device2.name = "Device 2";
        device2.thisDevice = false;
        device2.operatingSystemName = "Windows";
        device2.operatingSystemType = Device.Os.WINDOWS;
        device2.ipAddressV4 = "192.168.1.101";
        device2.secretKey = "secret2";

        // add the devices to the list
        devices.add(device1);
        devices.add(device2);

        for(int i=0; i<2; i++){
            getDeviceDao().insert(devices.get(i));
        }

        LiveData<List<Device>> liveData = getDeviceDao().getAll();
        List<Device> insertedDevices = liveData.getValue();

        assertNotNull(insertedDevices);
        assertTrue(insertedDevices.size() == 2);
        assertEquals(devices.get(0).uuid, insertedDevices.get(0).uuid);
        assertEquals(devices.get(1).uuid, insertedDevices.get(1).uuid);
    }

    @Test
    public void updateDevice() throws InterruptedException {
        Device device = new Device();
        device.name = "Device 1";
        device.thisDevice = true;
        device.operatingSystemName = "Android";
        device.operatingSystemType = Device.Os.ANDROID;
        device.ipAddressV4 = "192.168.1.100";
        device.secretKey = "secret1";

        getDeviceDao().insert(device);

        Device updatedDevice = new Device();
        updatedDevice.uuid = device.uuid;
        updatedDevice.name = "Updated Device";

        getDeviceDao().update(updatedDevice);

        LiveData<List<Device>> liveData = getDeviceDao().getAll();
        List<Device> insertedDevices = liveData.getValue();

        assertNotNull(insertedDevices);
        assertTrue(insertedDevices.size() == 1);
        assertEquals(updatedDevice.uuid, insertedDevices.get(0).uuid);
        assertEquals(updatedDevice.name, insertedDevices.get(0).name);
    }

}
