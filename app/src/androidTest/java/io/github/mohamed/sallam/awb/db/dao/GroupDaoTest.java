package io.github.mohamed.sallam.awb.db.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SmallTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.UUID;

import io.github.mohamed.sallam.awb.db.UserDatabaseTest;
import io.github.mohamed.sallam.awb.db.entity.Group;
import io.github.mohamed.sallam.awb.util.LiveDataTestUtil;
import io.github.mohamed.sallam.awb.util.TestUtil;
import kotlinx.coroutines.ExperimentalCoroutinesApi;

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4.class) //search for a better annotation //search for its purpose!
@SmallTest
public class GroupDaoTest extends UserDatabaseTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    private GroupDao groupDao ;

    @Before
    public void setup() {
        super.setup();
        groupDao = userDatabase.groupDao(); //setup //why is
    }

    @Test
    public void insert_insertGroupThenRead_returnTrue() throws InterruptedException {
        Group group = new Group(TestUtil.TEST_GROUP_1); //problem importing test-common

        // insert
        groupDao.insert(group); // wait until inserted //problem importing blockingGet

        // read
        LiveDataTestUtil<List<Group>> liveDataTestUtil = new LiveDataTestUtil<>(); //problem importing test-common
        List<Group> insertedGroups = liveDataTestUtil.getValue(groupDao.getAllForThisDevice()); //problem importing test-common

        assertNotNull(insertedGroups);

        assertEquals(group.deviceUuid, insertedGroups.get(0).deviceUuid);
        assertEquals(group.uuid, insertedGroups.get(0).uuid);
        assertEquals(group.name , (insertedGroups.get(0).name));  //checking group entity attributes
    }

    @Test
    public void rename_insertGroupThenRenameThenRead_returnTrue() throws InterruptedException {
        Group group = new Group(TestUtil.TEST_GROUP_1); //problem importing test-common

        // insert
        groupDao.insert(group); // wait until inserted //problem importing blockingGet

        // rename
        groupDao.rename(group.uuid , "group2");

        // read
        LiveDataTestUtil<List<Group>> liveDataTestUtil = new LiveDataTestUtil<>();
        List<Group> insertedGroups = liveDataTestUtil.getValue(groupDao.getAllForThisDevice());

        assertNotNull(insertedGroups);

        assertEquals(group.deviceUuid, insertedGroups.get(0).deviceUuid);
        assertEquals(group.uuid, insertedGroups.get(0).uuid);
        assertEquals(group.name  , (insertedGroups.get(0).name));
    }

    @Test
    public void delete_insertGroupThenReadThenDelete_returnTrue() throws InterruptedException {
        Group group = new Group(TestUtil.TEST_GROUP_1);

        // insert
        groupDao.insert(group);

        // read
        LiveDataTestUtil<List<Group>> liveDataTestUtil = new LiveDataTestUtil<>();
        List<Group> insertedGroups = liveDataTestUtil.getValue(groupDao.getAllForThisDevice());

        //  delete
        groupDao.delete(group.uuid);

        // confirm the database is empty
        insertedGroups = liveDataTestUtil.getValue(groupDao.getAllForThisDevice());
        assertEquals(0, insertedGroups.size());
    }

    @Test
    public void get_insertGroupThenReadThenGet_returnTrue() throws InterruptedException {
        Group group = new Group(TestUtil.TEST_GROUP_1);

        // insert
        groupDao.insert(group);

        //read
        LiveDataTestUtil<List<Group>> liveDataTestUtil = new LiveDataTestUtil<>();
        List<Group> insertedGroups = liveDataTestUtil.getValue(groupDao.getAllForThisDevice());

        //get
        LiveDataTestUtil<Group> liveDataTestUtil2 = new LiveDataTestUtil<>();

        Group insertedGroups2 = liveDataTestUtil2.getValue(groupDao.get(group.uuid));

        assertEquals(insertedGroups2.name , (insertedGroups.get(0).name));
        assertEquals(insertedGroups2.deviceUuid , insertedGroups.get(0).deviceUuid);
        assertEquals(insertedGroups2.uuid , insertedGroups.get(0).uuid);

    }

    @Test
    public void getAllByDevice_insertGroupThenReadThenGetAllByDevice_returnTrue() throws InterruptedException {
        Group group = new Group(TestUtil.TEST_GROUP_1);

        // insert
        groupDao.insert(group);

        //read
        LiveDataTestUtil<List<Group>> liveDataTestUtil = new LiveDataTestUtil<>();
        List<Group> insertedGroups = liveDataTestUtil.getValue(groupDao.getAllForThisDevice());

        //get
        Group insertedGroups2 =  liveDataTestUtil.getValue(groupDao.getAllByDevice(group.deviceUuid)).get(0);

        assertEquals(insertedGroups2.name, insertedGroups.get(0).name);
        assertEquals(insertedGroups2.deviceUuid, insertedGroups.get(0).deviceUuid);
        assertEquals(insertedGroups2.uuid, insertedGroups.get(0).uuid);
    }

    @Test
    public void replaceDeviceUuid_insertGroupThenReplaceDeviceUuidThenRead_returnTrue()  {
        Group group = new Group(TestUtil.TEST_GROUP_1);

        // insert
        groupDao.insert(group);

        // replaceDeviceUuid
        UUID newDeviceUuid = UUID.randomUUID();
        groupDao.replaceDeviceUuid(group.deviceUuid , newDeviceUuid);

        assertEquals(group.deviceUuid , newDeviceUuid);
    }
}
