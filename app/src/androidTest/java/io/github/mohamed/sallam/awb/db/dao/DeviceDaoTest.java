package io.github.mohamed.sallam.awb.db.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.test.filters.SmallTest;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import io.github.mohamed.sallam.awb.db.UserDatabaseTest;
import io.github.mohamed.sallam.awb.db.entity.Device;
import io.github.mohamed.sallam.awb.util.LiveDataTestUtil;
import io.github.mohamed.sallam.awb.util.TestUtil;
import kotlinx.coroutines.ExperimentalCoroutinesApi;

/**
 * Testing Device Data Access Object.
 *
 * @author Mohamed Sherif
 */
@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4.class)
@SmallTest
public class DeviceDaoTest extends UserDatabaseTest {

    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    private DeviceDao deviceDao;

    @Before
    public void setup() {
        super.setup();
        deviceDao = userDatabase.deviceDao();
    }

    @Test
    public void insertAndGetAllDevices() throws InterruptedException {
        // Arrange
        List<Device> devices = new ArrayList<>();

        Device device1 = new Device(TestUtil.TEST_DEVICE_1);
        Device device2 = new Device(TestUtil.TEST_DEVICE_1);

        devices.add(device1);
        devices.add(device2);

        // Act
        for(int i=0; i<2; i++){
            deviceDao.insert(devices.get(i));
        }

        LiveDataTestUtil<List<Device>> liveDataTestUtil = new LiveDataTestUtil<>();
        List<Device> insertedDevices = liveDataTestUtil.getValue(deviceDao.getAll());

        // Assert
        assertNotNull(insertedDevices);
        assertTrue(insertedDevices.size() == 2);
        assertEquals(devices.get(0).uuid, insertedDevices.get(0).uuid);
        assertEquals(devices.get(1).uuid, insertedDevices.get(1).uuid);
    }

    @Test
    public void updateDevice() throws InterruptedException {
        // Arrange
        Device device = new Device(TestUtil.TEST_DEVICE_1);
        Device updatedDevice = new Device(TestUtil.TEST_DEVICE_2);;
        updatedDevice.uuid = device.uuid;
        updatedDevice.name = "Updated Device";

        // Act
        deviceDao.insert(device);
        deviceDao.update(updatedDevice);

        LiveDataTestUtil<List<Device>> liveDataTestUtil = new LiveDataTestUtil<>();
        List<Device> insertedDevices = liveDataTestUtil.getValue(deviceDao.getAll());

        // Assert
        assertNotNull(insertedDevices);
        assertTrue(insertedDevices.size() == 1);
        assertEquals(updatedDevice.uuid, insertedDevices.get(0).uuid);
        assertEquals(updatedDevice.name, insertedDevices.get(0).name);
    }

    @Test
    public void deleteDevice() throws InterruptedException {
        // Arrange
        List<Device> devices = new ArrayList<>();

        Device device1 = new Device(TestUtil.TEST_DEVICE_1);
        Device device2 = new Device();

        devices.add(device1);
        devices.add(device2);

        for(int i=0; i<2; i++){
            deviceDao.insert(devices.get(i));
        }

        // Act
        deviceDao.delete(devices.get(0).uuid);

        LiveDataTestUtil<List<Device>> liveDataTestUtil = new LiveDataTestUtil<>();
        List<Device> insertedDevices = liveDataTestUtil.getValue(deviceDao.getAll());

        // Assert
        assertNotNull(insertedDevices);
        assertTrue(insertedDevices.size() == 1);
        assertEquals(devices.get(1).uuid, insertedDevices.get(0).uuid);
    }
}
