package io.github.mohamed.sallam.awb;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class DeviceWithGroups {
    @Embedded
    public Device device;
    @Relation(
            parentColumn = "uuid",
            entityColumn = "deviceUuid"
    )
    public List<Group> groups;
}
