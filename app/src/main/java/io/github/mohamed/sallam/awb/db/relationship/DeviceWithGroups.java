package io.github.mohamed.sallam.awb.db.relationship;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

import io.github.mohamed.sallam.awb.db.entity.Device;
import io.github.mohamed.sallam.awb.db.entity.Group;

public class DeviceWithGroups {
    @Embedded
    public Device device;
    @Relation(
            parentColumn = "uuid",
            entityColumn = "deviceUuid"
    )
    public List<Group> groups;
}
