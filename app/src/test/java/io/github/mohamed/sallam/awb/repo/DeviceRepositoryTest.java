package io.github.mohamed.sallam.awb.repo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

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

import io.github.mohamed.sallam.awb.db.dao.GroupDao;
import io.github.mohamed.sallam.awb.db.dao.DeviceDao;
import io.github.mohamed.sallam.awb.db.entity.Device;
import io.github.mohamed.sallam.awb.util.LiveDataTestUtil;
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
        groupDao = mock(GroupDao.class);
        deviceDao = mock(DeviceDao.class);
        deviceRepository = new DeviceRepository(groupDao, deviceDao);
    }

    @Test
    public void testInsert_DaoInsertCalled() {
        // Arrange
        Device device = TestUtil.TEST_DEVICE_1;

        // Act
        deviceRepository.insert(device);

        // Assert
        verify(deviceDao).insert(device);
        verifyNoMoreInteractions(deviceDao);
    }

    @Test
    public void testUpdate_DaoUpdateCalled() {
        // Arrange
        Device device = TestUtil.TEST_DEVICE_1;

        // Act
        deviceRepository.update(device);

        // Assert
        verify(deviceDao).update(device);
        verifyNoMoreInteractions(deviceDao);
    }

    @Test
    public void testDelete_daoDeleteCalled() {
        // Arrange
        Device device = TestUtil.TEST_DEVICE_1;

        // Act
        deviceRepository.delete(device.uuid);

        // Assert
        verify(deviceDao).delete(device.uuid);
        verifyNoMoreInteractions(deviceDao);
    }

    @Test
    public void testGenerateUuid_DaoMethodsCalled() {
        // Arrange
        UUID oldDeviceUuid = TestUtil.TEST_UUID_1;
        UUID newDeviceUuid = TestUtil.TEST_UUID_3;

        // Act
        deviceRepository.generateUuid(oldDeviceUuid);

        // Assert
        verify(groupDao).replaceDeviceUuid(oldDeviceUuid, newDeviceUuid);
        verify(deviceDao).setUuid(oldDeviceUuid, newDeviceUuid);
        verifyNoMoreInteractions(groupDao, deviceDao);
    }

    @Test
    public void testGetAll_ReturnsLiveDataDevices() throws InterruptedException {
        // Arrange
        List<Device> deviceList = new ArrayList<>();
        deviceList.add(TestUtil.TEST_DEVICE_1);
        LiveData<List<Device>> liveDataDevices = new MutableLiveData<>(deviceList);
        when(deviceDao.getAll()).thenReturn(liveDataDevices);

        // Act
        List<Device> result = new LiveDataTestUtil<List<Device>>().getValue(
                deviceRepository.getAll()
        );

        // Assert
        assertEquals(liveDataDevices.getValue(), result);
    }

    @Test
    public void testGetThisDevice_ReturnsLiveDataDevice() {
        // Arrange
        Device device = TestUtil.TEST_DEVICE_1;
        LiveData<Device> liveDataDevice = new MutableLiveData<>(device);
        when(deviceDao.getThisDevice()).thenReturn(liveDataDevice);

        // Act
        LiveData<Device> result = deviceRepository.getThisDevice();

        // Assert
        assertEquals(liveDataDevice, result);
    }
}