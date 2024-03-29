package io.github.mohamed.sallam.awb.db.entity;

import androidx.room.Entity;
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
}

