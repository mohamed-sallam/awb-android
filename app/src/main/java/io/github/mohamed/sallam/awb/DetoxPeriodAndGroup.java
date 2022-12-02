package io.github.mohamed.sallam.awb;

import androidx.room.Embedded;
import androidx.room.Relation;

public class DetoxPeriodAndGroup {
    @Embedded
    public DetoxPeriod detoxPeriod;
    @Relation(
            parentColumn = "uuid",
            entityColumn = "groupUuid"
    )
    public Group group;
}
