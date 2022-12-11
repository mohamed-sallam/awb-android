package io.github.mohamed.sallam.awb.db.relationship;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

import io.github.mohamed.sallam.awb.db.entity.WhitelistedApp;

public class DetoxPeriodAndGroupWithWhitelistedApps {
    @Embedded
    public DetoxPeriodAndGroup detoxPeriodAndGroup;
    @Relation(
            parentColumn = "groupUuid",
            entityColumn = "groupUuid"
    )
    public List<WhitelistedApp> whitelistedApps;
}
