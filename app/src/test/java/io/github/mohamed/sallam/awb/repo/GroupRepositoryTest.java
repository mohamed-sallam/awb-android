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

//    @Test
//    public void testInsertWhitelistedApp() {
//        WhitelistedApp whitelistedApp = new WhitelistedApp();
//        groupRepository.insertWhitelistedApp(whitelistedApp);
//        verify(whitelistedAppDao).insert(whitelistedApp);
//    }

//    @Test
//    public void testDeleteWhitelistedApp() {
//        UUID groupUuid = UUID.randomUUID();
//        String packageName = "com.example.app";
//        groupRepository.deleteWhitelistedApp(groupUuid, packageName);
//        verify(whitelistedAppDao).delete(groupUuid, packageName);
//    }
//
//    @Test
//    public void testGetAllWhitelistedAppsByGroupUuid() throws InterruptedException {
//        UUID groupUuid = UUID.randomUUID();
//        List<WhitelistedApp> whitelistedApps = new ArrayList<>();
//        whitelistedApps.add(new WhitelistedApp());
//        LiveData<List<WhitelistedApp>> liveData = new MutableLiveData<>(whitelistedApps);
//        when(whitelistedAppDao.getAllByGroupUuid(groupUuid)).thenReturn(liveData);
//
//        LiveDataTestUtil<List<WhitelistedApp>> liveDataTestUtil = new LiveDataTestUtil<>();
//        List<WhitelistedApp> result = liveDataTestUtil.getValue(groupRepository.getAllWhitelistedAppsByGroupUuid(groupUuid));
//        assertEquals(whitelistedApps, result);
//    }

//    @Test
//    public void testClone() {
//    }
//
//    @Test
//    public void getAllForThisDevice() {
//    }
}