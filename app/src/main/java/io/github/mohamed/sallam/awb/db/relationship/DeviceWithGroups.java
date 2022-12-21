package io.github.mohamed.sallam.awb.db.relationship;

import androidx.annotation.Nullable;
import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;
import java.util.Objects;

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

    // TODO write equal method to check if the device and it's group are the same.
    @Override
    public boolean equals(@Nullable Object obj) {
        return super.equals(obj);
    }
}
