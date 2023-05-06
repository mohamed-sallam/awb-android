package io.github.mohamed.sallam.awb.screen.updategroup;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static io.github.mohamed.sallam.awb.util.TestUtil.TEST_UUID_1;
import static io.github.mohamed.sallam.awb.util.TestUtil.TEST_APPS_LIST;
import static io.github.mohamed.sallam.awb.util.TestUtil.TEST_WHITELISTED_APPS_LIST;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import io.github.mohamed.sallam.awb.App;
import io.github.mohamed.sallam.awb.db.UserDatabase;
import io.github.mohamed.sallam.awb.db.entity.Device;
import io.github.mohamed.sallam.awb.db.entity.WhitelistedApp;
import io.github.mohamed.sallam.awb.repo.GroupRepository;
import io.github.mohamed.sallam.awb.util.LiveDataTestUtil;
import io.github.mohamed.sallam.awb.util.MainThreadExecutorService;

@RunWith(MockitoJUnitRunner.class)
public class UpdateGroupViewModelTest {
    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    private GroupRepository groupRepository;
    private UUID groupUuid;
    private UpdateGroupViewModel updateGroupViewModel;

    @Before
    public void setUp() {
        UserDatabase.databaseWriteExecutor = new MainThreadExecutorService();
        groupRepository = mock(GroupRepository.class);
        groupUuid = TEST_UUID_1;
        MutableLiveData<List<App>> apps = new MutableLiveData<>(TEST_APPS_LIST);
        MutableLiveData<List<WhitelistedApp>> whitelistedApps =
                new MutableLiveData<>(TEST_WHITELISTED_APPS_LIST);
        updateGroupViewModel = new UpdateGroupViewModel(groupRepository,
                                                        groupUuid, apps, whitelistedApps);

    }

    @Test
    public void testGetApps() {
        List<App> expectedApps = new ArrayList<>(TEST_APPS_LIST);
        Assert.assertEquals(expectedApps, updateGroupViewModel.getApps().getValue());
    }

    @Test
    public void testGetWhitelistedApps() {
        List<WhitelistedApp> expectedWhitelistedApps = new ArrayList<>(TEST_WHITELISTED_APPS_LIST);
        Assert.assertEquals(expectedWhitelistedApps,
                            updateGroupViewModel.getWhitelistedApps().getValue());
    }

//    @Test
//    public void resetNavigation() {
//    }
//
//    @Test
//    public void allowApp() {
//    }
//
//    @Test
//    public void blockApp() {
//    }
//
//    @Test
//    public void save() {
//    }
}