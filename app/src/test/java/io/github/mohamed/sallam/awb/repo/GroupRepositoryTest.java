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
import io.github.mohamed.sallam.awb.db.dao.WhitelistedAppDao;
import io.github.mohamed.sallam.awb.db.entity.Group;
import io.github.mohamed.sallam.awb.db.entity.WhitelistedApp;
import io.github.mohamed.sallam.awb.util.LiveDataTestUtil;
import io.github.mohamed.sallam.awb.util.TestUtil;

@RunWith(MockitoJUnitRunner.class)
public class GroupRepositoryTest {
    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();
    private GroupDao groupDao;
    private WhitelistedAppDao whitelistedAppDao;
    private GroupRepository groupRepository;

    @Before
    public void setUp() {
        groupDao = mock(GroupDao.class);
        whitelistedAppDao = mock(WhitelistedAppDao.class);
        groupRepository = new GroupRepository(groupDao, whitelistedAppDao);
    }

    @Test
    public void testInsert() {
        // Arrange
        Group group = TestUtil.TEST_GROUP_1;

        // Act
        groupRepository.insert(group);

        // Assert
        verify(groupDao).insert(group);
        verifyNoMoreInteractions(groupDao);
    }

    @Test
    public void testRename() {
        // Arrange
        UUID uuid = TestUtil.TEST_UUID_1;
        String name = "New Group Name";

        // Act
        groupRepository.rename(uuid, name);

        // Assert
        verify(groupDao).rename(uuid, name);
        verifyNoMoreInteractions(groupDao);
    }

    @Test
    public void testDelete() {
        // Arrange
        UUID groupUuid = TestUtil.TEST_UUID_1;

        // Act
        groupRepository.delete(groupUuid);

        // Assert
        verify(groupDao).delete(groupUuid);
        verify(whitelistedAppDao).deleteByGroupUuid(groupUuid);
        verifyNoMoreInteractions(groupDao);
        verifyNoMoreInteractions(whitelistedAppDao);
    }

    @Test
    public void testGetAllByDevice() throws InterruptedException {
        // Arrange
        UUID deviceUuid = TestUtil.TEST_UUID_1_DEVICE;
        List<Group> groups = new ArrayList<>();
        groups.add(TestUtil.TEST_GROUP_1);
        LiveData<List<Group>> liveData = new MutableLiveData<>(groups);
        when(groupDao.getAllByDevice(deviceUuid)).thenReturn(liveData);

        // Act
        List<Group> result = new LiveDataTestUtil<List<Group>>().getValue(
                groupRepository.getAllByDevice(deviceUuid)
        );

        // Assert
        assertEquals(groups, result);
    }

    @Test
    public void testInsertWhitelistedApp() {
        // Arrange
        WhitelistedApp whitelistedApp = TestUtil.TEST_WHITELISTED_APP_1;

        // Act
        groupRepository.insertWhitelistedApp(whitelistedApp);

        // Assert
        verify(whitelistedAppDao).insert(whitelistedApp);
        verifyNoMoreInteractions(whitelistedAppDao);
    }

    @Test
    public void testDeleteWhitelistedApp() {
        // Arrange
        UUID groupUuid = TestUtil.TEST_UUID_3;
        String packageName = "com.example.app";

        // Act
        groupRepository.deleteWhitelistedApp(groupUuid, packageName);

        // Assert
        verify(whitelistedAppDao).delete(groupUuid, packageName);
        verifyNoMoreInteractions(whitelistedAppDao);
    }

    @Test
    public void testGetAllWhitelistedAppsByGroupUuid() throws InterruptedException {
        // Arrange
        UUID groupUuid = TestUtil.TEST_UUID_3;
        List<WhitelistedApp> whitelistedApps = new ArrayList<>();
        whitelistedApps.add(TestUtil.TEST_WHITELISTED_APP_1);
        LiveData<List<WhitelistedApp>> liveData = new MutableLiveData<>(whitelistedApps);
        when(whitelistedAppDao.getAllByGroupUuid(groupUuid)).thenReturn(liveData);

        // Act
        List<WhitelistedApp> result = new LiveDataTestUtil<List<WhitelistedApp>>().getValue(
                groupRepository.getAllWhitelistedAppsByGroupUuid(groupUuid)
        );

        // Assert
        assertEquals(whitelistedApps, result);
    }

    @Test
    public void testGetAllForThisDevice() throws InterruptedException {
        // Arrange
        List<Group> groups = new ArrayList<>();
        groups.add(TestUtil.TEST_GROUP_1);
        LiveData<List<Group>> liveData = new MutableLiveData<>(groups);
        when(groupDao.getAllForThisDevice()).thenReturn(liveData);

        // Act
        List<Group> result = new LiveDataTestUtil<List<Group>>().getValue(
                groupRepository.getAllForThisDevice()
        );

        // Assert
        assertEquals(groups, result);
    }
}