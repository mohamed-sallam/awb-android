package io.github.mohamed.sallam.awb.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "lock_table")
public class secretkeyEntity  {
    @PrimaryKey(autoGenerate = false)
    int secretKey; // secretkey datatype ??

    public secretkeyEntity(int secretKey) {
        this.secretKey = secretKey;
    }

    public int getSecretKey() {
        return secretKey;
    }
}
