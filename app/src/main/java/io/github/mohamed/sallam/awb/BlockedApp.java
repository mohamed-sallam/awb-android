package io.github.mohamed.sallam.awb;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.UUID;

@TypeConverters({UuidConverter.class})
@Entity(tableName = "blocked_apps_table")
public class BlockedApp {
    // Fields
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String path;
    public UUID groupUuid;
}

