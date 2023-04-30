package io.github.mohamed.sallam.awb.db.dao;


import android.app.Application;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SmallTest;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.github.mohamed.sallam.awb.db.UserDatabase;
import kotlinx.coroutines.ExperimentalCoroutinesApi;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;



@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class) //search for a better annotation //search for its purpose!
@SmallTest

public class GroupDaoTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private GroupDao groupDao ; //final??
    UserDatabase db ; //database instance ??





    @Before
    public void setup(Application application) {
        db = UserDatabase.getInstance(application);
        groupDao = db.groupDao(); //setup //why is

    }



    @After
    public void teardown() {
        db.close();
    }

/*
    @BeforeClass

    @AfterClass

*/


    @Test
    public void testInsert() {
    }

    @Test
    public void testRename() {
    }

    @Test
    public void testDelete() {
    }

    @Test
    public void testGet() {
    }

    @Test
    public void testGetAllByDevice() {
    }

    @Test
    public void testReplaceDeviceUuid() {
    }

    @Test
    public void testGetAllForThisDevice() {
    }


}
