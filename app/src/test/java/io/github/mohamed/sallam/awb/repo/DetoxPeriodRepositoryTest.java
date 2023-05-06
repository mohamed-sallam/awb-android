package io.github.mohamed.sallam.awb.repo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.github.mohamed.sallam.awb.db.UserDatabase;
import io.github.mohamed.sallam.awb.db.dao.DetoxPeriodDao;
import io.github.mohamed.sallam.awb.db.entity.DetoxPeriod;
import io.github.mohamed.sallam.awb.db.entity.WhitelistedApp;
import io.github.mohamed.sallam.awb.util.LiveDataTestUtil;
import io.github.mohamed.sallam.awb.util.MainThreadExecutorService;
import io.github.mohamed.sallam.awb.util.TestUtil;

@RunWith(MockitoJUnitRunner.class)
public class DetoxPeriodRepositoryTest {

    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();
    private DetoxPeriod detoxPeriod;
    private DetoxPeriodDao detoxPeriodDao;
    private DetoxPeriodRepository detoxPeriodRepository;

    @Before
    public void setUp() {
        UserDatabase.databaseWriteExecutor = new MainThreadExecutorService();
        detoxPeriod  = TestUtil.TEST_DETOX_PERIOD_1;
        detoxPeriodDao = mock(DetoxPeriodDao.class);
        LiveData<List<WhitelistedApp>> liveDataDevices = new MutableLiveData<>(TestUtil.TEST_WHITELISTED_APPS_LIST);
        detoxPeriodRepository = new DetoxPeriodRepository(detoxPeriodDao, detoxPeriod, liveDataDevices);
    }

    @Test
    public void testInsert() {
        // Arrange
        DetoxPeriod detoxPeriod = TestUtil.TEST_DETOX_PERIOD_1;

        // Act
        detoxPeriodRepository.insert(detoxPeriod);


        // Assert
        verify(detoxPeriodDao).insert(detoxPeriod);
        verifyNoMoreInteractions(detoxPeriodDao);
    }

    @Test
    public void testUpdate() {
        // Arrange
        DetoxPeriod detoxPeriod = TestUtil.TEST_DETOX_PERIOD_1;

        // Act
        detoxPeriodRepository.update(detoxPeriod);

        // Assert
        verify(detoxPeriodDao).update(detoxPeriod);
        verifyNoMoreInteractions(detoxPeriodDao);
    }

    @Test
    public void testDelete() {
        // Act
        detoxPeriodRepository.delete();

        // Assert
        verify(detoxPeriodDao).delete();
        verifyNoMoreInteractions(detoxPeriodDao);
    }

    @Test
    public void testGet() throws InterruptedException {
        // Arrange
        DetoxPeriod detoxPeriod = TestUtil.TEST_DETOX_PERIOD_1;
        LiveData<DetoxPeriod> liveDataDetoxPeriod = new MutableLiveData<>(detoxPeriod);

        // Act
        DetoxPeriod result = new LiveDataTestUtil<DetoxPeriod>().getValue(
                detoxPeriodRepository.get()
        );

        // Assert
        assertEquals(liveDataDetoxPeriod.getValue(), result);
    }

    @Test
    public  void testGetWhitelistedAppsOfCurrentDetoxPeriod() throws InterruptedException {
        // Arrange
        List<WhitelistedApp> whitelistedAppList = new ArrayList<>();
        whitelistedAppList.add(TestUtil.TEST_WHITELISTED_APP_1);
        whitelistedAppList.add(TestUtil.TEST_WHITELISTED_APP_2);
        LiveData<List<WhitelistedApp>> liveDataDevices = new MutableLiveData<>(whitelistedAppList);

        // Act
        List<WhitelistedApp> result = new LiveDataTestUtil<List<WhitelistedApp>>().getValue(
                detoxPeriodRepository.getWhitelistedAppsOfCurrentDetoxPeriod()
        );

        // Assert
        assertEquals(liveDataDevices.getValue(), result);
    }
}