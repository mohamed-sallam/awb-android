package io.github.mohamed.sallam.awb.repo;

import static org.mockito.Mockito.*;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import io.github.mohamed.sallam.awb.db.UserDatabase;
import io.github.mohamed.sallam.awb.db.dao.DetoxPeriodDao;
import io.github.mohamed.sallam.awb.db.entity.DetoxPeriod;
import io.github.mohamed.sallam.awb.util.MainThreadExecutorService;
import io.github.mohamed.sallam.awb.util.TestUtil;

@RunWith(MockitoJUnitRunner.class)
public class DetoxPeriodRepositoryTest {

    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();
    private DetoxPeriodDao detoxPeriodDao;
    private DetoxPeriodRepository detoxPeriodRepository;

    @Before
    public void setUp() {
        UserDatabase.databaseWriteExecutor = new MainThreadExecutorService();
        detoxPeriodDao = mock(DetoxPeriodDao.class);
        detoxPeriodRepository = new DetoxPeriodRepository(detoxPeriodDao);
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
}