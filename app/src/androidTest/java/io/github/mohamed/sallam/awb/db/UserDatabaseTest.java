package io.github.mohamed.sallam.awb.db;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SmallTest;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

import io.github.mohamed.sallam.awb.db.dao.DeviceDao;

/**
 * Testing our internal database.
 *
 * @author Mohamed Sherif
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class UserDatabaseTest {

    private UserDatabase userDatabase;

    /**
     * Making a new instance of the database before every test case.
     *
     * Providing that this instance is created in the ram that
     * to be easy for testing.
     */
    @Before
    public void setup(){
        userDatabase = Room.inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                UserDatabase.class
        ).build();
    }

    /**
     * Closing the instance with the end of every test case.
     */
    @After
    public void tearDown(){
        userDatabase.close();
    }

    public DeviceDao getDeviceDao(){

    }

}
