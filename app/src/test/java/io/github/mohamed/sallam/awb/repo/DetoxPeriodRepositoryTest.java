package io.github.mohamed.sallam.awb.repo;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import org.junit.*;

import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import io.github.mohamed.sallam.awb.db.dao.DetoxPeriodDao;


@RunWith(MockitoJUnitRunner.class)
public class DetoxPeriodRepositoryTest {

    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();
    private DetoxPeriodDao detoxPeriodDao;
    private DetoxPeriodRepository detoxPeriodRepository;

    @Before
    public void setUp() {
    }

    @Test
    public void testInsert() {
    }

    @Test
    public void testUpdate() {
    }

    @Test
    public void testDelete() {
    }
}