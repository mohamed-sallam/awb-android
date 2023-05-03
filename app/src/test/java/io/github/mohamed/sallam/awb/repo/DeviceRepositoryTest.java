package io.github.mohamed.sallam.awb.repo;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import io.github.mohamed.sallam.awb.db.UserDatabase;
import io.github.mohamed.sallam.awb.db.dao.GroupDao;
import io.github.mohamed.sallam.awb.db.dao.DeviceDao;
import io.github.mohamed.sallam.awb.db.entity.Device;
import io.github.mohamed.sallam.awb.util.LiveDataTestUtil;
import io.github.mohamed.sallam.awb.util.MainThreadExecutorService;
import io.github.mohamed.sallam.awb.util.TestUtil;

@RunWith(MockitoJUnitRunner.class)
public class DeviceRepositoryTest {
    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();
    private GroupDao groupDao;
    private DeviceDao deviceDao;
    private DeviceRepository deviceRepository;

    @Before
    public void setUp() {
        UserDatabase.databaseWriteExecutor = new MainThreadExecutorService();
        groupDao = mock(GroupDao.class);
        deviceDao = mock(DeviceDao.class);
        LiveData<List<Device>> liveDataDevices = new MutableLiveData<>(TestUtil.TEST_DEVICE_LIST);
        deviceRepository = new DeviceRepository(groupDao, deviceDao,
                                                TestUtil.TEST_DEVICE_1, liveDataDevices);
    }

    @Test
    public void testInsert() {
        // Arrange
        Device device = TestUtil.TEST_DEVICE_1;

        // Act
        deviceRepository.insert(device);

        // Assert
        verify(deviceDao).insert(device);
        verifyNoMoreInteractions(deviceDao);
    }

    @Test
    public void testUpdate() {
        // Arrange
        Device device = TestUtil.TEST_DEVICE_1;

        // Act
        deviceRepository.update(device);

        // Assert
        verify(deviceDao).update(device);
        verifyNoMoreInteractions(deviceDao);
    }

    @Test
    public void testDelete() {
        // Arrange
        UUID deviceUuid = TestUtil.TEST_UUID_3;

        // Act
        deviceRepository.delete(deviceUuid);

        // Assert
        verify(deviceDao).delete(deviceUuid);
        verifyNoMoreInteractions(deviceDao);
    }

    @Test
    public void testGenerateUuid() {
        // Arrange
        UUID oldDeviceUuid = TestUtil.TEST_UUID_1;

        // Act
        deviceRepository.generateUuid(oldDeviceUuid);

        // Assert
        verify(groupDao).replaceDeviceUuid(eq(oldDeviceUuid), any(UUID.class));
        verify(deviceDao).setUuid(eq(oldDeviceUuid), any(UUID.class));
        verifyNoMoreInteractions(groupDao, deviceDao);
    }

    @Test
    public void testGetAll() throws InterruptedException {
        // Arrange
        List<Device> deviceList = new ArrayList<>();
        deviceList.add(TestUtil.TEST_DEVICE_1);
        deviceList.add(TestUtil.TEST_DEVICE_2);
        LiveData<List<Device>> liveDataDevices = new MutableLiveData<>(deviceList);

        // Act
        List<Device> result = new LiveDataTestUtil<List<Device>>().getValue(
                deviceRepository.getAll()
        );

        // Assert
        assertEquals(liveDataDevices.getValue(), result);
    }

    @Test
    public void testGetThisDevice() throws InterruptedException {
        // Arrange
        Device device = TestUtil.TEST_DEVICE_1;
        LiveData<Device> liveDataDevice = new MutableLiveData<>(device);

        // Act
        Device result = new LiveDataTestUtil<Device>().getValue(
                deviceRepository.getThisDevice()
        );

        // Assert
        assertEquals(liveDataDevice.getValue(), result);
    }
}