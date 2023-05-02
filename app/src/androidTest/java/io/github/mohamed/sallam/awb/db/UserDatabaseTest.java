package io.github.mohamed.sallam.awb.db;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SmallTest;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
@SmallTest
public class UserDatabaseTest {
    private UserDatabase userDatabase;

    @Before
    public void setup(){
        userDatabase = Room.inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                UserDatabase.class
        ).build();
    }

    @After
    public void tearDown(){
        userDatabase.close();
    }
}
