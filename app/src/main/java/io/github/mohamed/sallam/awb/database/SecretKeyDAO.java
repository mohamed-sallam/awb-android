package io.github.mohamed.sallam.awb.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import javax.crypto.SecretKey;

/**
 * Secret Key Data Access Object
 * @author Mohamed Sherif
 */
@Dao
public interface SecretKeyDAO {

    @Insert
    void insertSecretKey(SecretKey... SecretKey);

    @Query("DELETE FROM SecretKey NOT key=:key")
    void deleteAllExcept(String key);

}
