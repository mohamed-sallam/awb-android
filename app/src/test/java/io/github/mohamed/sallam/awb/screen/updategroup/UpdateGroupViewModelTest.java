package io.github.mohamed.sallam.awb.screen.updategroup;
import static org.mockito.Mockito.mock;

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
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import io.github.mohamed.sallam.awb.App;
import io.github.mohamed.sallam.awb.db.UserDatabase;
import io.github.mohamed.sallam.awb.db.entity.WhitelistedApp;
import io.github.mohamed.sallam.awb.repo.GroupRepository;
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

    /**
     * Test the getApps method of UpdateGroupViewModel.
     * Scenario: Verify that the method returns a list of apps that matches the TEST_APPS_LIST.
     * Expected result: The list of apps returned by the method matches the TEST_APPS_LIST inserted.
     */
    @Test
    public void testGetApps() {
        List<App> expectedApps = new ArrayList<>(TEST_APPS_LIST);
        Assert.assertEquals(expectedApps, updateGroupViewModel.getApps().getValue());
    }

    /**
     * Test the getWhitelistedApps method of UpdateGroupViewModel.
     * Scenario: Verify that the method returns a list of whitelisted apps that matches
     *           the TEST_WHITELISTED_APPS_LIST.
     * Expected result: The list of whitelisted apps returned by the method matches
     *                  the TEST_WHITELISTED_APPS_LIST inserted.
     */
    @Test
    public void testGetWhitelistedApps() {
        List<WhitelistedApp> expectedWhitelistedApps = new ArrayList<>(TEST_WHITELISTED_APPS_LIST);
        Assert.assertEquals(expectedWhitelistedApps,
                            updateGroupViewModel.getWhitelistedApps().getValue());
    }

    /**
     * Test the allowApp method of UpdateGroupViewModel.
     * Scenario: Verify that the method adds an AppCommand for the given package name to the
     *           appCommands map, with isAllowCommand set to true.
     * Expected result: The appCommands map should contain an AppCommand for the given package name,
     *                  with isAllowCommand set to true.
     */
    @Test
    public void testAllowApp() {
        String packageName = "com.example.app1";
        updateGroupViewModel.allowApp(packageName);
        Assert.assertEquals(1, updateGroupViewModel.appCommands.size());
        Assert.assertTrue(updateGroupViewModel.appCommands.get(packageName).isAllowCommand);
    }

    /**
     * Test the allowApp method of UpdateGroupViewModel when the app is blocked.
     * Scenario: Verify that the method does not add an AppCommand for a blocked package name
     *           to the appCommands map but delete/free the package to not have any command.
     * Expected result: AppCommands map should not contain any AppCommand for the given package name.
     */
    @Test
    public void testAllowAppIsBlocked() {
        String packageName = "com.example.app1";
        updateGroupViewModel.blockApp(packageName);
        updateGroupViewModel.allowApp(packageName);
        Assert.assertEquals(0, updateGroupViewModel.appCommands.size());
    }

    /**
     * Test the blockApp method of UpdateGroupViewModel.
     * Scenario: Verify that the method adds an AppCommand for the given package name to
     *           the appCommands map, with isAllowCommand set to false.
     * Expected result: The appCommands map should contain an AppCommand for the given package name,
     *                  with isAllowCommand set to false.
     */
    @Test
    public void testBlockApp() {
        String packageName = "com.example.app1";
        updateGroupViewModel.blockApp(packageName);
        Assert.assertEquals(1, updateGroupViewModel.appCommands.size());
        Assert.assertFalse(updateGroupViewModel.appCommands.get(packageName).isAllowCommand);
    }

    /**
     * Test the blockApp method of UpdateGroupViewModel.
     * Scenario: Verify that the method does not add an AppCommand for an allowed package name
     *           to the appCommands map but delete/free the package to not have any command.
     * Expected result: AppCommands map should not contain any AppCommand for the given package name.
     */
    @Test
    public void testBlockAppIsAllowed() {
        String packageName = "com.example.app1";
        updateGroupViewModel.allowApp(packageName);
        updateGroupViewModel.blockApp(packageName);
        Assert.assertEquals(0, updateGroupViewModel.appCommands.size());
    }

//    @Test
//    public void testSave() {
//    }
}