package io.github.mohamed.sallam.awb.db.relationship;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

import io.github.mohamed.sallam.awb.db.entity.Device;
import io.github.mohamed.sallam.awb.db.entity.Group;

/**
 * `DeviceWithGroups` class is a relationship between two entities `Device`
 * and `Group` in order to query a Device with its internal groups
 * from database by using DAO method. It's one-to-many relationship.
 *
 * Source: https://developer.android.com/training/data-storage/room/relationships
 */
public class DeviceWithGroups {
    @Embedded
    public Device device;
    @Relation(
            parentColumn = "uuid",
            entityColumn = "deviceUuid"
    )
    public List<Group> groups;
}
