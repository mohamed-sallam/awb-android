package io.github.mohamed.sallam.awb.db.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.UUID;

import io.github.mohamed.sallam.awb.db.converter.UuidConverter;

/**
 * Class `WhitelistedApp` to define the structure of an application object.
 *
 * @author Abdalrhman Hemida
 * @author Mohamed Sallam
 */
@TypeConverters({UuidConverter.class})
@Entity(
        tableName = "whitelisted_apps_table",
        indices = {
                @Index(
                        value = {"packageName", "groupUuid"},
                        unique = true
                )
        }
)
public class WhitelistedApp {
    // Fields
    @PrimaryKey(autoGenerate = true)
    public Integer id;
    public String packageName;
    public UUID groupUuid;

    public WhitelistedApp() {
    }

    @Ignore
    public WhitelistedApp(@NonNull Integer id, String packageName, UUID groupUuid) {
        this.id = id;
        this.packageName = packageName;
        this.groupUuid = groupUuid;
    }
}

