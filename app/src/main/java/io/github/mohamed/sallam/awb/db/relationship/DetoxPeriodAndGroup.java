package io.github.mohamed.sallam.awb.db.relationship;

import androidx.room.Embedded;
import androidx.room.Relation;

import io.github.mohamed.sallam.awb.db.entity.DetoxPeriod;
import io.github.mohamed.sallam.awb.db.entity.Group;

public class DetoxPeriodAndGroup {
    @Embedded
    public DetoxPeriod detoxPeriod;
    @Relation(
            parentColumn = "groupUuid",
            entityColumn = "uuid"
    )
    public Group group;
}
